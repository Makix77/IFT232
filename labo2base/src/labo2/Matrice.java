package labo2;

/*
 * Classe représentant une matrice. Composée d'une liste
 * de vecteurs. Chaque rangée de la matrice est un vecteur.
 */
public class Matrice {

	private Vecteur[] lignes;
	private double epsilon = 0.0001;

	public Matrice(double[][] dat) {

		lignes = new Vecteur[dat.length];

		for (int i = 0; i < dat.length; i++) {

			lignes[i] = new Vecteur(dat[i]);

		}
	}
	private Matrice(){
		lignes = new Vecteur[0];
	}

	/*
	 * Élimination Gaussienne. Let me google that for you... Implémentation
	 * suivant le pseudo-code classique.
	 */
	public void Gauss() {

		int noLigne = 0;
		for (Vecteur ligne : lignes) {
			double pivot = ligne.getValeur(noLigne);
			if (pivot != 0) {
				double pivotInverse = 1.0 / pivot;
				for (int i = 0; i < ligne.taille(); i++) {
					ligne.setValeur(i, ligne.getValeur(i) * pivotInverse);
				}
			}

			for (Vecteur ligneElim : lignes) {
				if (ligneElim != ligne) {
					double f = ligneElim.getValeur(noLigne);
					for (int i = 0; i < ligneElim.taille(); ++i) {
						ligneElim.setValeur(i, ligneElim.getValeur(i) - f * ligne.getValeur(i));
					}
				}
			}
			noLigne++;
		}
	}
	
	public String toString() {

		String res = "";

		for (Vecteur v : lignes) {
			res += v + "\n";
		}

		return res;
	}

	public Matrice sousMatrice(int lignes, int colonnes){
		if(lignes > this.lignes.length || lignes < 0 || colonnes < 0)
			throw new IllegalArgumentException("Dimensions inadmissibles.");
		for(int i = 0; i < this.lignes.length; i++){
			if(colonnes > this.lignes[i].taille())
				throw new IllegalArgumentException("Dimensions inadmissibles.");
		}
		double[][] nouveau = new double[lignes][colonnes];
		for(int i = 0; i < lignes; i++){
			double[] v = new double[colonnes];
			for(int j = 0; j < colonnes; j++){
				v[j] = this.lignes[i].getValeur(j);
			}
			nouveau[i] = v;
		}
		Matrice m = new Matrice(nouveau);
		return m;
	}

	public static Matrice creerMatriceNulle (int lignes, int colonnes){
		if(lignes < 0 || colonnes < 0)
			throw new IllegalArgumentException("Dimensions inadmissibles.");
		double[][] mat = new double[lignes][colonnes];
		for(int i = 0; i < lignes; i++){
			double[] ligne = new double[colonnes];
			for(int j = 0; j < colonnes; j++){
				ligne[j] = 0;
			}
			mat[i] = ligne;
		}
		Matrice m = new Matrice(mat);
		return m;
	}

	public static Matrice creerMatriceIdentite (int taille){
		if(taille < 0)
			throw new IllegalArgumentException("Dimensions inadmissibles.");
		double[][] mat = new double[taille][taille];
		for(int i = 0; i < taille; i++){
			double[] ligne = new double[taille];
			for(int j = 0; j < taille; j++){
				if(j == i)
					ligne[j] = 1;
				else
					ligne[j] = 0;
			}
			mat[i] = ligne;
		}
		Matrice m = new Matrice(mat);
		return m;
	}

	public static boolean egaliteDoublePrecision(double a, double b, double epsilon){
		return (Math.abs(a-b) <= epsilon);
	}

	@Override
	public boolean equals(Object other){
		Matrice m = (Matrice) other;
		if(this.lignes.length != m.lignes.length)
			return false;
		for(int i = 0; i < this.lignes.length; i++){
			Vecteur ligneThis = this.lignes[i];
			Vecteur ligneOther = m.lignes[i];
			if(ligneThis.taille() != ligneOther.taille())
				return false;
			for(int j = 0; j < ligneThis.taille(); j++){
				if(!egaliteDoublePrecision(ligneThis.getValeur(j), ligneOther.getValeur(j), epsilon)){
					return false;
				}
			}
		}
		return true;
	}
}
