package labo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * Obtention de données test:
 * http://planetcalc.com/3571/
 * Classe "preuve de concept". Démontre l'utilisation des fonctions
 * et classes de base du labo. Exemple d'une mauvaise façon d'écrire 
 * des tests (surtout quand le code prend de l'expansion).
 * Contient des tests extrêmement minimaux, ne prouve pas beaucoup 
 * le bon fonctionnement du code écrit.
 */

public class UtilitairesAlgebre {

	public static void main(String[] args) {

		/*
		 * Quelques tests pêle-mêle.
		 */
				
		double[] s1l1 = { 1, 2, 3, 14 };

		Vecteur l1 = new Vecteur(s1l1);
		System.out.println("Test de vecteur:");
		System.out.println(l1);
		
		double[][] systeme1 = {

				{ 3, 5, -3, 15 },
				{ 7, 10, 1, 2 },
				{ -3, 2, -5, 6 }
			};

		Matrice mat = new Matrice(systeme1);
		mat.Gauss();
		System.out.println("Test de matrice:");
		System.out.println(mat);

		/*
		 * Ensemble de données déclarées pour effectuer des tests.
		 */
		ArrayList<Character> variables = new ArrayList<Character>();
		ArrayList<Integer> coefficients = new ArrayList<Integer>();
		int constante;
		String equation = "3x + 5y - 3z = 15";
		
		constante = lireEquation(equation, variables, coefficients);
		String resultat = afficherEquation(variables, coefficients, constante);
		System.out.println("Test d'équation:");
		System.out.println(resultat);
		
		/*
		 * Tests pour un système d'équations.
		 */
		
		double systeme [][]=new double[3][];
		
		systeme[0]=coefficientAtableau(coefficients,constante);
		
		variables = new ArrayList<Character>();
		coefficients = new ArrayList<Integer>();
		String equation2 = "7x + 10y + 1z = 2";
		
		constante = lireEquation(equation2, variables, coefficients);
		systeme[1]=coefficientAtableau(coefficients,constante);
		
		variables = new ArrayList<Character>();
		coefficients = new ArrayList<Integer>();
		String equation3 = "-3x + 2y -5z = 6";
		
		constante = lireEquation(equation3, variables, coefficients);
		systeme[2]=coefficientAtableau(coefficients,constante);
		
		Matrice sysEquations = new Matrice(systeme);
		System.out.println("Système avant résolution:");
		System.out.println(afficherSystemeEquations(variables, systeme));
		System.out.println(sysEquations);
		sysEquations.Gauss();
		System.out.println("Système après résolution:");		
		System.out.println(sysEquations);

	}
	
	public static double[] coefficientAtableau(ArrayList<Integer> liste, int constante) {
		double valeurs[] = new double[liste.size()+1];
		int i=0;
		for(int coef: liste) {
			valeurs[i]=coef;
			i++;
		}
		valeurs[liste.size()]=constante;
		return valeurs;
	}

	/*
	 * Fonction qui transforme en String une équation linéaire à partir une liste de
	 * variables et de coefficients séparées.
	 */
	public static String afficherEquation(ArrayList<Character> variables, ArrayList<Integer> coefficients,
			double constante) {

		String res = "";
		res += coefficients.get(0) + "" + variables.get(0);

		for (int i = 1; i < variables.size(); i++) {

			int coeff = coefficients.get(i);
			if (coeff < 0) {
				res += " - ";
			} else {
				res += " + ";
			}

			res += Math.abs(coeff) + "" + variables.get(i);
		}

		res += " = " + constante;
		return res;

	}

	/*
	 * Fonction qui extrait une liste de variables (une lettre par variable) et
	 * une liste des coefficients qui leur correspond d'une String contenant une
	 * une équation linéaire. Extrait également la constante à droite du =.
	 * Lance des exceptions dans plusieurs des cas problématiques.
	 */
	public static int lireEquation(String source, ArrayList<Character> variables, ArrayList<Integer> coefficients) {

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

				variables.add(var);
				coefficients.add(coeff * lastSign);
			}
		}
		if (tokenizer.hasMoreTokens())
			throw new IllegalArgumentException("Equation: équation mal formée (expression continue après la constante");
		if (!complete)
			throw new IllegalArgumentException("Equation: équation mal formée (manque = constante à la fin)");

		return constante;

	}
	
	
	public static String afficherSystemeEquations(ArrayList<Character> variables, double[][] valeurs) {
		String res="";
		
		
		for(double[] equation: valeurs) {
			ArrayList<Integer> coefficients = new ArrayList<Integer>();
			for(int i=0;i<equation.length-1;i++) {
				coefficients.add((int)equation[i]);
			}
			res+=afficherEquation(variables,coefficients,(int)equation[equation.length-1])+"\n";
		}
		return res;
	}

	/*
	 * Fonctions utilitaires pour reconnaître les opérateurs.
	 */

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
