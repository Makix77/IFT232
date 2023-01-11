package fusee;

public class Citerne {
    public static void main(String[] args){

    }
    int hauteur;
    public Citerne(int hauteur){
        this.hauteur = hauteur;
    }

    public String dessiner(){
        String citerne = "";
        for(int i = 0; i < this.hauteur; i++){
            citerne += " |  |\n";
        }
        return citerne;
    }
}
