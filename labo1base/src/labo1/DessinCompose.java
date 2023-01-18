package labo1;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Scanner;
public class DessinCompose extends Dessin {

    private ArrayList<Dessin> dessins;
    public DessinCompose(){
        dessins = new ArrayList<>();
    }
    public void dessiner(Graphics2D graph) {
        for(Dessin d : dessins){
            d.dessiner(graph);
        }
    }


    public void lire(Scanner reader) {
        boolean end = false;
        while(!end){
            int next = reader.nextInt();
            if(next < 0){
                switch(next){
                    case -1:
                        lirePoint(reader);
                        break;
                    case -2:
                        lireLigne(reader);
                        break;
                    case -3:
                        lireNuage(reader);
                        break;
                    case -4:
                        lirePolygone(reader);
                        break;
                    default:
                        return;
                }
            }
        }
    }

    private void lirePoint(Scanner reader){
        Point p = new Point();
        p.lire(reader);
        dessins.add(p);
    }
    private void lireLigne(Scanner reader){
        Ligne l = new Ligne();
        l.lire(reader);
        dessins.add(l);
    }
    private void lireNuage(Scanner reader){
        NuagePoint n = new NuagePoint();
        n.lire(reader);
        dessins.add(n);
    }
    private void lirePolygone(Scanner reader){
        Polygone po = new Polygone();
        po.lire(reader);
        dessins.add(po);
    }
}
