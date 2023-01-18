package labo1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NuagePoint extends Dessin {

    protected ArrayList<Point> points;

    public NuagePoint(){
        points = new ArrayList<>();
    }

    public void dessiner(Graphics2D graph){
        for(Point p : points){
            p.dessiner(graph);
        }
    }

    public void lire(Scanner reader){
        int taille = reader.nextInt();
        points = new ArrayList<>(taille);
        while(reader.hasNextInt()){
            Point p = new Point();
            p.lire(reader);
            points.add(p);
        }
    }

    public String toString(){
        String ligne = "";
        for (Point p : points){
            ligne += p.toString() + "\n";
        }
        return ligne;
    }
}
