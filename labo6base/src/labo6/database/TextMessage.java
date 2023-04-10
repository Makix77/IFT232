package labo6.database;

import java.util.Random;

import labo6.Ressources.Gender;

public class TextMessage {
	
	public enum TextKey {
		language(0),
		isQuestion(1),
		isOffensive(2),
		isSeductive(3),
		isPassiveAggressive(4),
		isGreeting(5),
		forGender(6);
		
		
		private int value;
		private TextKey(int v){
			value=v;
		}
	}	
	
	
	public enum Language {
		french(0), english(1);

		public int value;

		private Language(int v) {
			value = v;
		}
		
		public static Language random(){
			Random generator = new Random();
				
			return values()[generator.nextInt(values().length)];
		}
	}
	
	private Object values[];
	private String message;
	
	
	public TextMessage(String m, Language l, boolean q, boolean o,boolean s,boolean pa,boolean g,Gender gen) {
		
		values = new Object[7];
		message=m;
		values[TextKey.language.value]=l;
		values[TextKey.isQuestion.value]=q;
		values[TextKey.isOffensive.value]=o;
		values[TextKey.isSeductive.value]=s;	
		values[TextKey.isPassiveAggressive.value]=pa;
		values[TextKey.isGreeting.value]=g;
		values[TextKey.forGender.value]=gen;
	}
	
	public boolean match(TextKey key, Object value){
		return values[key.value].equals(value);
	}
	
	public String getMessage(){
		return message;
	}

}
