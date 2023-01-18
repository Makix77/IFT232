package labo1;

import java.awt.Graphics2D;
import java.util.Scanner;

public class Ligne extends Dessin {

    private Point p1;
    private Point p2;

    public Ligne(){
        p1 = new Point();
        p2 = new Point();
    }

    public Ligne(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    public void dessiner(Graphics2D graph){
        graph.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void lire(Scanner reader){
        p1.lire(reader);
        p2.lire(reader);
    }

    public String toString(){
        return p1.toString() + " -> " + p2.toString();
    }
}
