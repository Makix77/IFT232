package jarvis.atoms;

import jarvis.interpreter.JarvisInterpreter;

//SOUSLECAPOT
/*
 * Un atome est une unité de base reconnue par l'interpréteur.
 * Les atomes de base sont:
 *  (les types primitif)
 *  entiers - IntAtom
 *  booléens - BoolAtom
 *  liste - ListAtom
 *  table de hashage - DictionnaryAtom
 *  null - NullAtom
 *  objet - ObjectAtom
 *  string - StringAtom
 *  
 *  L'interprétation de ces atomes ne produit aucun changement notable,
 *  autre que de les mettre dans la file d'arguments de l'interpréteur
 *  dans certains cas.
 *  
 *  De plus, le type ClosureAtom implante la fermeture, 
 *  une suite de commandes Jarvis qui s'exécutent dans leur environnement
 *  avec des paramètres.
 *  
 *  L'interprétation d'une fermeture crée un environnement, lie les 
 *  arguments aux paramètres et empile les commandes sur la pile de 
 *  l'interpréteur, ce qui ressemble à l'évaluation d'une fonction.
 *  
 *  Finalement, CommandAtom représente toute entrée non interprétée.
 *  L'interprétation d'un atome de ce type a habituellement un effet
 *  sur l'interpréteur et son environnement, comme faire des commandes
 *  spéciales ou fabriquer d'autres atomes.
 * 
 * 
 */

public abstract class AbstractAtom {
	
	protected boolean isUndefined=false;

	/*
	 * Interprétation d'un atome.
	 * Le code commun à tous les atomes.
	 */
	public void interpret(JarvisInterpreter ji) {
		AbstractAtom res = interpretNoPut(ji);
		
		/*
		 * Enfiler le résultat d'interprétation dans la file d'arguments sert
		 * à préparer des arguments pour un appel de méthode ou de closure.
		 * tout simplement en évaluant des variables ou des nouvelles valeurs.
		 * Les énoncés qui font évaluer des atomes enfilent les résultats 
		 * dans la file d'arguments.
		 */
		ji.putArg(res);
		
	}

	/*
	 * Chaque type d'atome a son interprétation différente.
	 * Les types les plus simples se retournent eux-mêmes (atomes indivisibles...)
	 * Le type ClosureAtom lance l'interprétation du code qu'il contient. 
	 */
	public abstract AbstractAtom interpretNoPut(JarvisInterpreter ji);

	public final void print(JarvisInterpreter ji) {
		ji.output(makeKey());
	}
	
	/*
	 * Présentement utilisé seulement par CommandAtom.
	 * Reste ici pour éviter des tentatives de coercitions 
	 * et tests de typage qui alourdiraient le code. 
	 * Potentiellement utile pour d'autres atomes dans le futur.
	 */
	public boolean isUndefined()
	{
		return isUndefined;
	}

	/*
	 * Transforme l'atome en string.
	 * toString demeure intacte pour obtenir l'affichage
	 * d'objets standard dans certains cas.
	 * Sert aussi à fabriquer des clés à partir d'atomes
	 * pour chercher dans un dictionnaire ou une liste.
	 * C'est ce mécanisme qui permet d'implanter les conditionnelles
	 * en conjonction avec la passation de messages, si le sélecteur
	 * est un atome au lieu d'un symbole.
	 */
	public abstract String makeKey();

}
