package labo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Equation extends Vecteur {
    private Vecteur variables;
    private Vecteur coefficients;
    private int constante;

    public Equation(String equation){
        variables = new Vecteur();
        coefficients = new Vecteur();
        constante = lire(equation);
    }

    public Vecteur getVariables(){
        return variables;
    }
    public Vecteur getCoefficients(){
        return coefficients;
    }
    public int getConstante(){
        return constante;
    }

    public String toString() {

        String res = "";
        res += (int)coefficients.getValeur(0) + "" + (char)variables.getValeur(0);

        for (int i = 1; i < variables.taille(); i++) {

            double coeff = coefficients.getValeur(i);
            if (coeff < 0.0) {
                res += " - ";
            } else {
                res += " + ";
            }

            res += (int)Math.abs(coeff) + "" + (char)variables.getValeur(i);
        }

        res += " = " + constante;
        return res;

    }

    public int lire(String source) {

        int constante = 0;
        final String delims = "+-=";
        HashMap<Character, Integer> signes = new HashMap<Character, Integer>();
        signes.put('+', 1);
        signes.put('-', -1);

        int lastSign = 1;
        boolean complete = false;

        StringTokenizer tokenizer = new StringTokenizer(source, delims, true);

        while (tokenizer.hasMoreTokens()) {

            String val = tokenizer.nextToken().trim();
            if (val.length() == 0)
                continue;

            if (isOperator(val)) {

                lastSign = signes.get(val.charAt(0));

            } else if (isOperatorEquals(val)) {

                if (!tokenizer.hasMoreTokens())
                    throw new IllegalArgumentException("Equation: équation mal formée (constante manquante à la fin");

                constante = Integer.parseInt(tokenizer.nextToken().trim());
                complete = true;
                break;

            } else {

                int coeff = Integer.parseInt(val.substring(0, val.length() - 1));
                char var = val.charAt(val.length() - 1);

                if (variables.contains(var))
                    throw new IllegalArgumentException("Equation: équation mal formée (variable dupliquée");

                variables.addValeur(var);
                coefficients.addValeur(coeff * lastSign);
            }
        }
        if (tokenizer.hasMoreTokens())
            throw new IllegalArgumentException("Equation: équation mal formée (expression continue après la constante");
        if (!complete)
            throw new IllegalArgumentException("Equation: équation mal formée (manque = constante à la fin)");

        return constante;

    }

    private static boolean isOperator(String c) {

        if (c.length() == 1) {
            return c.charAt(0) == '+' || c.charAt(0) == '-';
        }

        return false;

    }

    private static boolean isOperatorEquals(String c) {

        if (c.length() == 1) {
            return c.charAt(0) == '=';
        }

        return false;
    }
}
