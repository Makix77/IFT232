package labo6.bots;

import labo6.Ressources.Gender;
import labo6.User;
import labo6.check.CheckBehavior;
import labo6.check.CheckBehaviorImpatient;
import labo6.check.CheckBehaviorPatient;
import labo6.check.CheckBehaviorSlowmo;
import labo6.database.Picture;
import labo6.session.CasualSession;
import labo6.session.SeductionSession;
import labo6.session.Session;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorAsk;
import labo6.wait.WaitBehaviorNothing;
import labo6.wait.WaitBehaviorSaySomething;

public class ChatBot extends User {

	//L'utilisateur avec lequel le robot est en communication.
	protected User peer;
	private CheckBehavior check;
	private WaitBehavior wait;
	public ChatBot(User p, String n, Picture pic, Gender g) {
		super(n, pic, g);
		peer = p;
	}
	
	public void appendMessage(String msg){
		getUI().appendMessage(msg);
	}
	
	public User getPeer(){
		return peer;
	}

	public boolean checkForWakeUp(String text){
		return check.checkForWakeUp(text);
	}
	public void waitForUser() { wait.waitForUser(); }

	public void createCheckBehavior(String type){
		this.check = CheckBehavior.createCheckBehavior(type);
	}
	public void createWaitBehavior(String type){
		this.wait = WaitBehavior.createWaitBehavior(type);
	}
}
