package labo1;

import java.awt.Graphics2D;
import java.util.Scanner;

public class Point extends Dessin {
	
	private int x;
	private int y;
	
	public Point(){
		x=0;
		y=0;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	/*
	 * Fonction qui dessine le point sur la surface 2D.
	 */
	
	public void dessiner(Graphics2D graph){
		graph.drawLine(x-10, y-10, x+10, y+10);
		graph.drawLine(x+10, y-10, x-10, y+10);
	}
	
	/*
	 * Fonction qui obtient les coordonnées du point 
	 * à partir d'un flot d'entiers
	 */
	public void lire(Scanner reader) {		
		x=reader.nextInt();
		y=reader.nextInt();		
		
	}

	public String toString(){
		return "(" + x + "," + y + ")";
	}
}
