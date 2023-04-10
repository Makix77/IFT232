package labo6;

import java.util.Random;

/*
 * Définitions et fonctions utilitaires.
 */

public class Ressources {

	public enum Gender {
		male(0), female(1), unspecified(2);

		public int value;

		private Gender(int v) {
			value = v;
		}
		
		public static Gender random(){
			Random generator = new Random();
				
			return values()[generator.nextInt(values().length)];
		}
	}

	public enum Country {
		Canada(0), Quebec(1), France(2), Japan(3), UnitedStates(4);

		public int value;

		private Country(int v) {
			value = v;
		}

		public Country next() {

			return values()[(value + 1) % values().length];

		}
	}

	

	public static final String[] flagImages = { "images/canflag.png", "images/quebecflag.png", "images/franceflag.png",
			"images/japflag.png", "images/usflag.png"

	};

	
	public static final String[] genderIcons = {

			"images/masicon.png", "images/femicon.png", "images/unkicon.png" };

	
}
