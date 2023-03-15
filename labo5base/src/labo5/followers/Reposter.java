package labo5.followers;

import labo5.Labo5Main;
import labo5.ui.MessageView;

public class Reposter implements Follower {

	protected MessageView viewport;
	private String name;
	
	/*
	 * Un reposter est un fan de la célébrité. 
	 */
	public Reposter(Labo5Main ui,String n){
		name=n;
		viewport = new MessageView(ui,name+" news feed");		
	}
		
	
	/*
	 * LABO4: code de mise à jour de l'affichage.
	 * Un reposter est un fan qui ne fait que ré-afficher ce que la vedette
	 * a dit dans son propre fil de nouvelles
	 */
	@Override
	public void update(String message, String celebName){
		//Ajoute tout simplement le message dans la boîte de texte.
		viewport.appendMessage(message);
	}
	
}
