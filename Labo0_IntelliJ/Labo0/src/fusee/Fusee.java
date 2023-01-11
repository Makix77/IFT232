package fusee;

public class Fusee {
    public static void main(String[] args){
        Fusee fusee = new Fusee(5, 1000);

        System.out.println(fusee.dessiner());
    }
    public static final double GRAVITE = 9.8;
    public static final double MASSE = 470;
    public static final double IMPULSION = 282;
    public static final double VITESSE = 9700;
    Cargo cargo;
    Citerne citerne;
    Moteur moteur;

    public Fusee(int hauteur, double masseCargo){
        this.cargo = new Cargo(masseCargo);
        this.citerne = new Citerne(calculerMasseCombustible());
        this.moteur = new Moteur();
    }

    public double calculerMasseSeche(){
        return cargo.getMasse() + MASSE;
    }
    public double calculerMasseCombustible(){
        double rapportMasses = Math.exp(-1 *VITESSE/(IMPULSION*GRAVITE));
        double masseTotale = calculerMasseSeche()/rapportMasses;
        return (1-rapportMasses)*masseTotale;
    }

    public String dessiner(){
        String dessin = cargo.dessiner();
        dessin += citerne.dessiner();
        dessin += moteur.dessiner();

        return dessin;
    }
}
