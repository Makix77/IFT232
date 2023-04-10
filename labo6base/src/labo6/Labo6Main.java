package labo6;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import labo6.Ressources.Country;
import labo6.Ressources.Gender;
import labo6.bots.ChatBot;
import labo6.session.CasualSession;
import labo6.session.SeductionSession;
import labo6.session.Session;

public class Labo6Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel background;
	private User humanUser;

	private JPanel nextPanel = new JPanel();
	JButton nextButton = new JButton("SUIVANT");
	JButton genderButton = new JButton("GENRE");
	JButton countryButton = new JButton("PAYS");
	private Country userCountry = Country.Canada;
	private Gender userGender = Gender.male;	
	private Session session;
	private String sessionType = "";

	

	public Labo6Main(String[] args) {
		
		if(args.length>0){
			sessionType=args[0];
		}		
		
	}

	/*
	 * Boucle principale. Ne termine pas. D�marre une session avec l'utilisateur humain.
	 * La session se termine lorsqu'on appuie sur "GENRE" ou sur "PAYS": l'utilisateur humain
	 * change de caract�ristiques, la session ne peut donc plus continuer.
	 */
	public void startTheRoulette() {
		while (true) {
			humanUser = new User("Me", userCountry, userGender);
			session = createSession(Session.NORMAL_SESSION, this, humanUser);
			session.start();
		}
	}


	public static Session createSession(String type, Labo6Main ui, User autre){
		Session session = null;
		switch(type){
			case Session.NORMAL_SESSION:
				session = new Session(ui, autre);
				break;
			case Session.SEDUCTION_SESSION:
				session = new SeductionSession(ui, autre);
				break;
			case Session.CASUAL_SESSION:
				session = new CasualSession(ui, autre);
				break;
			default:
				throw new IllegalArgumentException("Wrong session type: "+type);
		}
		return session;
	}

	/*
	 * Initialisation de la fen�tre. Long script d�sagr�able. Ne vous pr�ocuppez pas de ce code.
	 */
	private void initUI() {

		setTitle("ChatRoomLette");
		setSize(1600, 1000);
		background = new JPanel(new GridLayout(1, 3));
		nextPanel.setLayout(new BoxLayout(nextPanel, BoxLayout.X_AXIS));
		nextPanel.add(Box.createHorizontalGlue());
		nextPanel.add(genderButton);
		nextPanel.add(countryButton);
		nextPanel.add(nextButton);
		nextPanel.add(Box.createHorizontalGlue());
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						session.changeChatBot();						
					}
				});
			}
		});

		genderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {

						userGender = (Gender.values())[(userGender.value + 1) % Gender.values().length];
						session.end();
					}
				});
			}
		});
		countryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {

						userCountry = userCountry.next();
						session.end();
					}
				});
			}
		});

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initBackGround(ChatBot robotUser) {
		this.getContentPane().removeAll();
		background = new JPanel(new GridLayout(1, 3));
		background.add(humanUser.getUI());
		background.add(nextPanel);
		background.add(robotUser.getUI());
		add(background);
		validate();
	}

	public static void main(String[] args) {

		/*
		 * Mise en file de l'ex�cution de l'interface graphique pour le
		 * EventDispatchThread (gestionnaire de fen�tres).
		 */
		final Labo6Main ex = new Labo6Main(args);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				ex.initUI();
				ex.setVisible(true);
			}
		});

		ex.startTheRoulette();
	}
}
