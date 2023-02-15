package labo2.tests;

import labo2.Matrice;
import org.junit.Before;
import org.junit.Test;

import labo2.Vecteur;

import static org.junit.Assert.*;

public class TestMatrice {

    double[][] systeme = {

            { 3, 5, -3, 15 },
            { 7, 10, 1, 2 },
            { -3, 2, -5, 6 }
    };
    double[][] systeme2 = {

            { 3, 5, -3, 15 },
            { 7, 10, 1, 2 }
    };
    double[][] systeme3 = {

            { 3, 8, -3, 15 },
            { 7, -10, 1, 2 },
            { -3, 1, -5,4 }
    };


    Matrice m = new Matrice(systeme);
    Matrice m2 = new Matrice(systeme2);
    Matrice m3 = new Matrice(systeme3);

    @Before
    public void setup(){

        m = new Matrice(systeme);
        m2 = new Matrice(systeme2);
        m3 = new Matrice(systeme3);
    }

    @Test
    public void testToString() {

        double[][] systeme1 = {

                { 3, 5, -3, 15 },
                { 7, 10, 1, 2 },
                { -3, 2, -5, 6 }
        };

        Matrice m1 = new Matrice(systeme1);

        String resAttendu = "[3.0 5.0 -3.0 15.0]\n[7.0 10.0 1.0 2.0]\n[-3.0 2.0 -5.0 6.0]\n";

        assertTrue(m1.toString().equals(resAttendu));
    }
    @Test
    public void testEquals(){
        double[][] systeme1 = {

                { 3, 5, -3, 15 },
                { 7, 10, 1, 2 },
                { -3, 2, -5, 6 }
        };

        Matrice m1 = new Matrice(systeme1);

        assertEquals(m, m1);

        assertNotEquals(m, m2);

        assertNotEquals(m, m3);

        double[][] systeme4 = {

                { 3, 5, -3, 15 },
                { 7, 10, 1 },
                { -3, 2, -5, 6 }
        };

        Matrice m4 = new Matrice(systeme4);

        assertNotEquals(m, m4);
    }

    @Test
    public void testGauss(){
        double[][] systeme = {
                { 1, 0, 0, 4.15625 },
                { 0, 1, 0, -2.25 },
                { 0, 0, 1, -4.59375 }
        };

        Matrice m1 = new Matrice(systeme);
        m.Gauss();
        assertEquals(m, m1);

        double[][] systeme3 = {
                { 1, 0, 0, 1.52754},
                { 0, 1, 0, 0.71186 },
                { 0, 0, 1, -1.57415 }
        };

        m1 = new Matrice(systeme3);
        m3.Gauss();
        assertEquals(m3, m1);
    }

    @Test
    public void testSousMatrice(){
        double[][] systeme = {
                {3, 5},
                {7, 10}
        };
        Matrice t = new Matrice(systeme);
        assertEquals(m.sousMatrice(2, 2), t);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSousMatriceTropGrandLignes(){
        m.sousMatrice(10, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSousMatriceTropGrandColonnes(){
        m.sousMatrice(1, 10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSousMatriceNegatifLignes(){
        m.sousMatrice(-2, 3);
    }@Test(expected = IllegalArgumentException.class)
    public void testSousMatriceNegatifColonnes(){
        m.sousMatrice(3, -1);
    }
    @Test
    public void testMatriceNulle(){
        double[][] systeme = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        Matrice m1 = new Matrice(systeme);
        Matrice m2 = Matrice.creerMatriceNulle(3, 3);
        assertEquals(m1, m2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatriceNulleNegatif(){
        Matrice m = Matrice.creerMatriceNulle(-2, 4);
    }

    @Test
    public void testMatriceIdentite(){
        double[][] systeme = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrice m1 = new Matrice(systeme);
        Matrice m2 = Matrice.creerMatriceIdentite(4);
        assertEquals(m1, m2);
    }

    @Test
    public void testMatriceGaussIdentite(){
        m.Gauss();
        Matrice m2 = m.sousMatrice(3, 3);
        Matrice m3 = Matrice.creerMatriceIdentite(3);
        assertEquals(m2, m3);
    }
}
