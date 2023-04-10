package labo6.database;

import labo6.Ressources.Gender;

public class PictureDatabase {

	private static final PictureList PICTURES = new PictureList();

	public static PictureList getAllPictures() {
		return (PictureList) PICTURES.clone();
	}

	static {

		PICTURES.add("images/bender.png", Gender.unspecified, true, true);
		PICTURES.add("images/goku.png", Gender.male, true, false);
		PICTURES.add("images/Troll.png", Gender.male, true, false);
		PICTURES.add("images/burger.png", Gender.male, false, false);
		PICTURES.add("images/barbie.png", Gender.female, false, false);
		PICTURES.add("images/cat.png", Gender.male, false, false);
		PICTURES.add("images/catseyes.png", Gender.female, true, true);
		PICTURES.add("images/corn.png", Gender.unspecified, false, false);
		PICTURES.add("images/diver.png", Gender.male, false, false);
		PICTURES.add("images/dog.png", Gender.unspecified, false, false);
		PICTURES.add("images/emmawatson.png", Gender.female, false, true);
		PICTURES.add("images/ryangosling.jpg", Gender.male, false, true);
		PICTURES.add("images/dolphin.png", Gender.unspecified, false, false);
		PICTURES.add("images/sakura.png", Gender.female, true, false);
		PICTURES.add("images/shark.png", Gender.unspecified, false, false);
		PICTURES.add("images/spike.png", Gender.male, true, true);
		PICTURES.add("images/towel.png", Gender.male, false, true);
		PICTURES.add("images/football2.png", Gender.male, false, false);

	}

}
