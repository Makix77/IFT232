package labo2;


/*
 * Classe représentant un vecteur. 
 */

import java.util.Arrays;

public class Vecteur {
	
	private double valeurs[];
	private double epsilon = 0.01;
	
	
	public Vecteur(double [] valeurs){
		
		this.valeurs = new double[valeurs.length];
		for(int i=0;i<valeurs.length;i++){
			
			this.valeurs[i]=valeurs[i];			
		}		
	}
	Vecteur(){
		this.valeurs = new double[0];
	}
	
	public String toString(){
		
		String res="[";
		for (double v:valeurs){
			res+=v+" ";
		}
		return  res.substring(0, res.length()-1)+"]";
	}
	public static boolean egaliteDoublePrecision(double a, double b, double epsilon){
		return (Math.abs(a-b) <= epsilon);
	}

	public Vecteur sousVecteur(int taille){
		if(taille > this.taille() || taille <= 0)
			throw new IllegalArgumentException("Dimensions inadmissibles.");
		double[] nouveau = new double[taille];
		for(int i = 0; i < taille; i++){
			nouveau[i] = this.getValeur(i);
		}
		Vecteur v = new Vecteur(nouveau);
		return v;
	}

	public static Vecteur creerVecteurNul(int taille){
		if(taille < 0)
			throw new IllegalArgumentException("Dimensions inadmissibles.");
		double[] tab = new double[taille];
		for(int i = 0; i < taille; i++){
			tab[i] = 0;
		}
		Vecteur v = new Vecteur(tab);
		return v;
	}

	@Override
	public boolean equals(Object other){
		Vecteur v = (Vecteur) other;
		if(this.taille() != v.taille())
			return false;
		for(int i = 0; i < v.taille(); i++){
			if(!egaliteDoublePrecision(this.getValeur(i), v.getValeur(i), epsilon))
				return false;
		}
		return true;
	}
	
	public int taille(){
		return valeurs.length;
	}
	
	public double getValeur(int pos){
		return valeurs[pos];
	}

	public boolean contains(double value){
		for(int i = 0; i < taille(); i++){
			if(valeurs[i] == value)
				return true;
		}
		return false;
	}

	public void addValeur(double value){
		double[] nouvellesValeurs = Arrays.copyOf(valeurs, taille()+1);
		nouvellesValeurs[taille()] = value;
		valeurs = nouvellesValeurs;
	}
	
	public void setValeur(int pos,double val){
		valeurs[pos]=val;
	}

	
}
