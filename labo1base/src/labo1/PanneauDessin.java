package labo1;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


/*
 * Panneau qui représente une surface pour dessiner.
 * S'initialise avec un Point à dessiner.
 * Dessine le point.
 */

class PanneauDessin extends JPanel implements ActionListener {

    
	private static final long serialVersionUID = 1L;
	
	//L'objet qui sera dessiné sur la surface.
	private Dessin dessin;
	
	//La surface de dessin doit être créée avec un objet à dessiner.
    public PanneauDessin(Dessin dessin) {

        this.dessin = dessin;
    }    

    /*
     * C'est ici que ça se passe! 
     * Principalement un appel à la fonction de dessin 
     * implantée par le contenu.
     */
    
    private void doDrawing(Graphics g) {

        Graphics2D graph = (Graphics2D) g;

        graph.setPaint(Color.black);      
        
        dessin.dessiner(graph);
        
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

