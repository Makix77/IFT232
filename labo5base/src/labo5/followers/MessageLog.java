package labo5.followers;

/*
 * Journal système. Affiche dans la console le message en question.
 * Utilisez-le pour voir si les messages sont produits comme prévu
 * par les vedettes.
 */
public class MessageLog implements Follower{
	@Override
	public void update(String message, String celebName){

		System.out.println(message);

	}
}
