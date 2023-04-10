package labo6.session;

import labo6.Labo6Main;
import labo6.Ressources.Gender;
import labo6.User;
import labo6.bots.ChatBot;
import labo6.database.*;
import labo6.profiler.NormalProfile;
import labo6.profiler.Profiler;


/*
 * Cette classe représente une session d'un utilisateur humain
 * avec un ou plusieurs robots.
 * La session se termine lorsqu'on détecte que l'utilisateur humain
 * s'est déconnecté (change de pays ou de genre, via les boutons "PAYS" et "GENRE").
 */

public class Session {
	public static final String NORMAL_SESSION = "normal";
	public static final String SEDUCTION_SESSION = "seduction";
	public static final String CASUAL_SESSION = "casual";
	private String sessionType = NORMAL_SESSION;
	private User human;
	private ChatBot robot;
	private Gender robotGender;
	protected Profiler profiler;
	private Labo6Main ui;
	private boolean ended;
	private Thread sleeper;

	public Session(Labo6Main l, User u) {
		ui = l;
		human = u;
		ended = false;
		profiler = createProfiler();
		sleeper = Thread.currentThread();
	}

	public static Profiler createProfiler(){
		return new NormalProfile();
	}

	public void start() {
		switch(sessionType){
			case NORMAL_SESSION, CASUAL_SESSION:
				robotGender = human.getGender();
				break;
			case SEDUCTION_SESSION:
				if(human.getGender() == Gender.male)
					robotGender = Gender.female;
				else
					robotGender = Gender.male;
				break;
		}

		robot = profiler.createChatBot(human, "Other", profiler.getSuitablePictures().random(), robotGender);
		ui.initBackGround(robot);

		robot.appendMessage(profiler.getSuitableMessages().keep(TextMessage.TextKey.isGreeting, true).random().getMessage());
		while (!hasEnded()) {

			robot.waitForUser();

			if (robot.checkForWakeUp(human.getUI().getText())) {

				robot.appendMessage(profiler.getSuitableMessages().random().getMessage());
			}

		}

	}
	
	/*
	 * Appelé par le bouton SUIVANT
	 */
	public void changeChatBot() {
		robot = profiler.createChatBot(human, "Other", PictureDatabase.getAllPictures().random(), Gender.random());
		ui.initBackGround(robot);
	}
	public synchronized void end() {
		ended = true;
		sleeper.interrupt();
	}
	private synchronized boolean hasEnded() {
		return ended;
	}
}
