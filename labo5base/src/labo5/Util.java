package labo5;

import java.util.Random;

public class Util {
	
	
	private static Random generator = new Random();
	
	
	public static final String DRAMATIC_STATUS[] ={
			"Feeling a little bit down today.",
			"Meditating on my life. It feels too short.",
			"So down right now. I feel so small and insignificant.",
			"Can't choose what to eat. Any suggestions?",
			"There's so much suffering in the world... can't cope right now...",
			"Anyone can suggest something positive to think about?",
			"Thank you, everyone, sincerely for your support.\nMy heart is still filled with sorrow."			
	};
	
	public static final String POSITIVE_STATUS[] ={
			"Life is about the road, not the destination.",
			"Being positive is the first step to happiness.",
			"The solution to every problem lies in your heart.",
			"You make you own luck.",
			"If your job makes you unhappy, find another job.",
			"Stop and take a deep breath. What do you smell?",
			"Ask yourself, what can I offer my loved ones today?"			
	};
	
	public static final String BROSCIENCE_STATUS[] ={
			"I use WHEY too much protein powder!",
			"My muscles are soo big",
			"I'm totally going for a new diet. That's some deep shit.",
			"Anyone can do more than 100 pullups?",
			"I just bench pressed 400lbs as a warmup.\n Ask yourself: What did you bench press today?",
			"Going big this weekend: 5 min plank challenge!",
			"Creatine is not drug, bro. The difference is small anyway."			
	};
	
	public static final String [] TROLL_DICTIONNARY1 = {
			"deep",
			"short",
			"small",
			"big"
	};
	
	public static final String TROLL_REPLY1 = "Yeah, that's just like, your opinion man!";
	
	public static final String [] TROLL_DICTIONNARY2 = {
			"?"
	};
	
	public static final String TROLL_REPLY2 = "DEEZ NUTS!";
	
	public static final String [] FANBOY_DICTIONNARY ={
			"That's so deep...",
			"Thank you for your bits of wisdom",
			"OMG Yes!! I think the same",
			"You never fail to amaze me"			
	};
	
	public static final String [] HATER_DICTIONNARY ={
			"Die already",
			"Your mom",
			"Crawl under a rock and die",
			"Geez you need a doctor to prescribe you some meds"
			
	};
	
	
	/*
	 * Obtient une chaîne de caractères au hasard parmi
	 * celles d'un dictionnaire.
	 */
	
	public static String getRandomString(String [] dict){
		
		return dict[generator.nextInt(dict.length)];
		
	}
	
	/*
	 * Ajoute un nom devant un message
	 */
	public static String personnalize(String name, String message){
		return "@" + name + ":\n" + message;
		
	}
	
	/*
	 * Vï¿½rifie si un message contient au moins un des mots d'un dictionnaire
	 */	
	public static boolean contains(String message,String[] dict){
		
		for(String word:dict){
			if(message.contains(word)){return true;}
		}
		return false;
	}

}
