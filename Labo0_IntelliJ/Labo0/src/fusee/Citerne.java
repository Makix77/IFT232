package fusee;

public class Citerne {
    public static void main(String[] args){

    }
    public static final double DENSITE_OXYGENE = 1.141;
    public static final double DENSITE_KEROSENE = 1.020;
    int hauteur;
    double masseCombustible;
    public Citerne(double masseCombustible){
        this.masseCombustible = masseCombustible;
        this.calculerHauteurMoteur();
    }


    public double calculerMasseKerosene(){
        return masseCombustible / 3.56;
    }
    public double calculerMasseOxygene(){
        return masseCombustible - calculerMasseKerosene();
    }
    public double calculerVolumeKerosene(){
        return calculerMasseKerosene() / Citerne.DENSITE_KEROSENE;
    }
    public double calculerVolumeOxygene(){
        return calculerMasseOxygene() / Citerne.DENSITE_OXYGENE;
    }
    public void calculerHauteurMoteur(){
        double hauteurExacte = (calculerVolumeKerosene() + calculerVolumeOxygene()) / ((Math.PI ) * Math.pow(Moteur.DIAMETRE_MOTEUR/2, 2) * 1000);
        this.hauteur = (int)hauteurExacte;
        System.out.println(this.hauteur);
    }

    public String dessiner(){
        String citerne = "";
        System.out.println(this.hauteur);
        for(int i = 0; i < this.hauteur; i++){
            citerne += " |  |\n";
        }
        return citerne;
    }
}
