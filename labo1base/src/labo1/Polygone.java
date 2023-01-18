package labo1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Polygone extends Dessin{

    private NuagePoint sommets;
    public Polygone(){
        sommets = new NuagePoint();
    }
    public void dessiner(Graphics2D graph) {
        for(int i = 0; i < sommets.points.size()-1; i++){
            Ligne l = new Ligne(sommets.points.get(i), sommets.points.get(i+1));
            l.dessiner(graph);
        }
        Ligne l = new Ligne(sommets.points.get(sommets.points.size()-1), sommets.points.get(0));
        l.dessiner(graph);
    }

    public void lire(Scanner reader) {
        int taille = reader.nextInt();
        for(int i = 0; i < taille; i++){
            Point p = new Point();
            p.lire(reader);
            sommets.points.add(p);
        }
    }
}
