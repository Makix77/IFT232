package labo5;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import labo5.celebrities.BroScienceCelebrity;
import labo5.celebrities.DramaticCelebrity;
import labo5.celebrities.PositiveCelebrity;
import labo5.celebrities.SocialMediaCelebrity;
import labo5.followers.*;
import labo5.ui.MessageView;

public class Labo5Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel background;
	private ArrayList<MessageView> views = new ArrayList<MessageView>();

	private ArrayList<SocialMediaCelebrity> celebs = new ArrayList<SocialMediaCelebrity>();
	private ArrayList<Reposter> fans = new ArrayList<Reposter>();
	private MessageLog log = new MessageLog();

	/*
	 * Initialisation des Celebrities et des Followers. établissement des liens
	 * entre eux.
	 */

	public Labo5Main(String[] args) {

		DramaticCelebrity dramaQueen = new DramaticCelebrity("BritneySpears", 2);
		PositiveCelebrity spiritualKing = new PositiveCelebrity("LookWithin", 3);
		BroScienceCelebrity superBro = new BroScienceCelebrity("JohnCena", 4);
		celebs.add(dramaQueen);
		celebs.add(spiritualKing);
		celebs.add(superBro);

		fans.add(new Reposter(this, "BritneyFan"));
		fans.add(new Reposter(this, "SpiritualFan"));
		fans.add(new Reposter(this, "JohnCenaFan"));
		SerialReposter nolife = new SerialReposter(this, "nolife");

		PressAgent pressAgent = new PressAgent();
		Troll troll1 = new Troll(this, "Troll1", Util.TROLL_DICTIONNARY1, Util.TROLL_REPLY1);
		Troll troll2 = new Troll(this, "Troll2", Util.TROLL_DICTIONNARY2, Util.TROLL_REPLY2);

		for(int i = 0; i < celebs.size(); i++){
			celebs.get(i).attach(fans.get(i));
			celebs.get(i).attach(log);
			celebs.get(i).attach(nolife);
			celebs.get(i).attach(troll1);
			celebs.get(i).attach(troll2);
			celebs.get(i).attachVeto(pressAgent);
		}

		Hater hater = new Hater(this, "Hater", 0);
		Fanboy fanboy = new Fanboy(this, "Fanboy", 0);

		dramaQueen.attach(hater);
		spiritualKing.attach(fanboy);

		hater.attach(new Reposter(this, "HaterFan"));
		hater.attach(nolife);
		hater.attach(troll1);
		hater.attach(troll2);
		hater.attach(log);
		fanboy.attach(new Reposter(this, "FanboyFan"));
		fanboy.attach(nolife);
		fanboy.attach(troll1);
		fanboy.attach(troll2);
		fanboy.attach(log);

	}
	
	/*
	 * Boucle qui change les statuts des vedettes à tour de rôle. 
	 * Drama knows no bounds! Ne terminera jamais (fermez la fenêtre pour
	 * arrêter le programme).
	 */

	public void startTheDrama() {

		while (true) {
			for(SocialMediaCelebrity celeb : celebs){
				celeb.changeStatus();
			}

		}

	}

	/*
	 * Initialisation de la fenêtre.
	 */
	private void initUI() {

		setTitle("FaceTwit");
		setSize(1600, 1000);
		background = new JPanel(new GridLayout(4, 5));
		for (MessageView view : views) {
			background.add(view);
		}

		add(background);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addToMainWindow(MessageView view) {
		views.add(view);
	}

	public static void main(String[] args) {

		/*
		 * Mise en file de l'exécution de l'interface graphique pour le
		 * EventDispatchThread (gestionnaire de fenêtres).
		 */
		Labo5Main ex = new Labo5Main(args);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				ex.initUI();
				ex.setVisible(true);
			}
		});

		ex.startTheDrama();
	}
}
