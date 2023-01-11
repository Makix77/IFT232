package fusee;

import static fusee.Fusee.MASSE;

public class Cargo {
    public static void main(String[] args){

    }
    double masse;

    public Cargo(double masse){
        this.masse = masse;
    }

    public double getMasse(){
        return this.masse;
    }

    public String dessiner(){
        String cargo = "  /\\ \n";
        cargo += " /__\\ \n";
        return cargo;
    }
}
