package labo2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import labo2.Equation;

import java.util.ArrayList;

public class TestEquation {
    String s1 = "3x + 5y -2z = 0";
    String s2 = "-4a + 6b + 12c = 7";
    Equation e1 = new Equation(s1);
    Equation e2 = new Equation(s2);

    @Before
    public void setup(){
        e1 = new Equation(s1);
        e2 = new Equation(s2);
    }

    @Test
    public void testLireEquation(){
        ArrayList<Character> c = new ArrayList<>();
        c.add('x');
        c.add('y');
        c.add('z');
        ArrayList<Integer> i = new ArrayList<>();
        i.add(3);
        i.add(5);
        i.add(-2);
        int constante = 0;
        assertEquals(c, e1.getVariables());
        assertEquals(i, e1.getCoefficients());
        assertEquals(constante, e1.getConstante());
    }

    @Test
    public void testToString(){
        String s = "-4a + 6b + 12c = 7";
        assertEquals(e2.toString(), s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquationSansConstante(){
        String s = "3x + 3y + 3z = ";
        Equation e = new Equation(s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquationVariableDupliquee(){
        String s = "3x + 3y + 3x = 3";
        Equation e = new Equation(s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquationContinue(){
        String s = "3x + 3y + 3z = 3 + 2 + 6 + 1";
        Equation e = new Equation(s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquationManqueEgal(){
        String s = "3x + 3y + 3z";
        Equation e = new Equation(s);
    }
}
