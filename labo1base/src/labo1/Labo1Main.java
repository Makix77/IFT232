package labo1;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFrame;

public class Labo1Main extends JFrame {

 
	private static final long serialVersionUID = 1L;

	
	
	
	/*
	 * Cette fonction initialise le dessin. Elle lit un Point
	 * au clavier ou à partir du fichier. 
	 */
	
	private Dessin initDessin(Scanner reader){

		Dessin d = new DessinCompose();
		
			d.lire(reader);
		
		return d;
	}
	
	
	
	
	/*
	 * Initialisation de la classe de test.
	 * On obtient un nom de fichier des paramètres d'exécution.
	 * Si on n'a pas de paramètre ou un fichier inexistant, on utilise
	 * le clavier comme flot d'entrée.
	 */
	
	public Labo1Main(String[] args) {	
		
		Scanner reader = new Scanner(System.in);
		
    	if(args.length ==1){
			
			String file = args[0];		
		
		try {
				System.out.println("Lecture du fichier" + file + "...");
				reader = new Scanner(new FileReader(file));				
		}

		catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé...");
			reader = new Scanner(new InputStreamReader(System.in));
		}
    	}
		else
		{
			System.out.println("Aucun fichier en paramètre. Entrez les données dans la console.");
			reader = new Scanner(System.in);
		}

        initUI(reader);
    }

	
	/*
	 * Initialisation de la fenêtre.
	 */
    private void initUI(Scanner reader) {
    	
    	//Obtention d'un point à partir du fichier ou du clavier
		Dessin dessin = initDessin(reader);

    	//Affichage sur la console de la valeur du point
    	System.out.println(dessin);
    	
    	//Panneau qui sert de surface pour dessiner.
        PanneauDessin surface = new PanneauDessin(dessin);
        
        //La surface de dessin est placée dans la fenêtre.
        add(surface);
        
        //Configurations relatives à la fenêtre.
        setTitle("Labo 1");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
    	
    	
    	/*
    	 * Mise en file de l'exécution de l'interface graphique pour le 
    	 * EventDispatchThread (gestionnaire de fenêtres).
    	 */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Labo1Main ex = new Labo1Main(args);
                ex.setVisible(true);
            }
        });
    }
}
