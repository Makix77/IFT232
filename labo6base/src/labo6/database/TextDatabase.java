package labo6.database;

import labo6.Ressources.Gender;
import labo6.database.TextMessage.Language;

public class TextDatabase {

	private static final TextList TEXT = new TextList();

	public static TextList getAllMessages() {
		return (TextList) TEXT.clone();
	}

	static {
		// Lang, quest, seduc, pass, greet, gend

		TEXT.add("Hi! How are you doing?", Language.english, true, false, false, false, true, Gender.unspecified);
		TEXT.add("Where are your wings? Cause I swear I thought you fell from heaven", Language.english, true, false,
				true, false, true, Gender.unspecified);
		TEXT.add("Look what the cat dragged in...", Language.english, false, false, false, false, true, Gender.unspecified);
		TEXT.add("What do you want? I'm busy!", Language.english, true, true, false, false, true, Gender.unspecified);
		TEXT.add("Here we go, another nice person to chat with...", Language.english, false, false, false, true, true,
				Gender.unspecified);
		TEXT.add("My cat's breath smells like cat food.", Language.english, false, false, false, false, false,
				Gender.unspecified);
		TEXT.add("My tummy feels funny.", Language.english, false, false, false, false, false, Gender.unspecified);
		TEXT.add("And I want a bike and a monkey and a friend for the monkey", Language.english, false, false, false,
				false, false, Gender.unspecified);
		TEXT.add("My cat's name is Mittens", Language.english, false, false, false, false, false, Gender.unspecified);
		TEXT.add("My parents won't let me use scissors. ", Language.english, false, false, false, false, false,
				Gender.unspecified);
		TEXT.add("When I grow up I wanna be a Principal or a Caterpillar", Language.english, false, false, false, false,
				false, Gender.unspecified);
		TEXT.add("I kissed a light socket once and I woke up in a helicopter", Language.english, false, false, false,
				false, false, Gender.unspecified);
		TEXT.add("Will you cook my dinner for me? My parents aren't around and I'm not allowed to turn on the stove. ",
				Language.english, true, false, false, false, false, Gender.unspecified);
		TEXT.add("I'm not a photographer, but I can picture me and you together", Language.english, false, false, true,
				false, false, Gender.unspecified);
		TEXT.add("Are you an interior decorator? Because when I saw you, the entire room became beautiful.",
				Language.english, true, false, true, false, false, Gender.unspecified);
		TEXT.add("Do you know what my shirt is made of? Boyfriend material.", Language.english, true, false, true,
				false, false, Gender.female);
		TEXT.add("I seem to have lost my phone number. Can I have yours?", Language.english, true, false, true, false,
				false, Gender.unspecified);
		TEXT.add("Do you have a map? I'm getting lost in your eyes.", Language.english, true, false, true, false, false,
				Gender.unspecified);
		TEXT.add("I don't have a library card, but do you mind if I check you out?", Language.english, true, true, true,
				false, false, Gender.unspecified);
		TEXT.add("I was feeling a little off today, but you definitely turned me on.", Language.english, true, true,
				true, false, false, Gender.unspecified);

		TEXT.add("Salut! Comment ça va?", Language.french, true, false, false, false, true, Gender.unspecified);
		TEXT.add("Où sont tes ailes? J'aurais pu jurer que t'étais tombée du ciel...", Language.french, true, false,
				true, false, true, Gender.female);
		TEXT.add("Eh bien, regarde qui s'amène...", Language.french, false, false, false, false, true, Gender.unspecified);
		TEXT.add("Kess tu veux? Chus occuppé là...", Language.french, true, true, false, false, true, Gender.unspecified);
		TEXT.add("Bon, encore une autre personne intéressante avec qui parler...", Language.french, false, false, false, true, true,
				Gender.unspecified);
		TEXT.add("L'haleine de mon chat sent le Meow Mix.", Language.french, false, false, false, false, false,
				Gender.unspecified);
		TEXT.add("J'ai des papillons dans l'estomac.", Language.french, false, false, true, false, false, Gender.unspecified);
		TEXT.add("Moi, j'aime les saucisses, pis les roches.", Language.french, false, false, false,
				false, false, Gender.unspecified);
		TEXT.add("Mon chat s'appelle Mistigri.", Language.french, false, false, false, false, false, Gender.unspecified);
		TEXT.add("Mes parent refusent que j'utilise les ciseaux.", Language.french, false, false, false, false, false,
				Gender.unspecified);
		TEXT.add("Quand je vais être grand, je veux devenir beta-testeur de jeux vidéo.", Language.french, false, false, false, false,
				false, Gender.unspecified);
		TEXT.add("J'ai collé ma langue sur la clôture pis je suis resté pris...", Language.french, false, false, false,
				false, false, Gender.unspecified);
		TEXT.add("Peux-tu m'aider à faire le souper? La recette dit de faire bouillir de l'eau pis je sais pas comment...",
				Language.french, true, false, false, false, false, Gender.unspecified);
		TEXT.add("Je ne suis pas photographe, mais je t'imagine dans toutes sortes de poses", Language.french, false, true, true,
				false, false, Gender.unspecified);
		TEXT.add("Es-tu décoratrice d'intérieur? Parce que je sans que je le feng, shui séduit par toi",
				Language.french, true, false, true, false, false, Gender.female);
		TEXT.add("T'as tu vu l'oeuvre d'art qu'est mon 6-pack?", Language.french, true, false, true, false,
				false, Gender.female);
		TEXT.add("J'ai perdu mon no de téléphone, tu peux me donner le tien?", Language.french, true, false, true, false,
				false, Gender.unspecified);
		TEXT.add("As-tu une carte? Je crois que je viens de me perdre dans tes yeux...", Language.french, true, false, true, false, false,
				Gender.unspecified);
		TEXT.add("Mon feu intéreieur s'était éteint jusqu'à ce que tu m'allumes.", Language.french, true, true,
				true, false, false, Gender.unspecified);

	}

}
