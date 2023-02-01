package jarvis.atoms.primitives.integers;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.IntAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.atoms.primitives.PrimitiveOperationAtom;
import jarvis.interpreter.JarvisInterpreter;


/* OPERATIONSPRIMITIVES
 * Classe de base pour les primitives de la classe int en Jarvis.
 * Les opérations de base travaillent toutes sur la valeur du IntAtom qui
 * se trouve dans le champ "value" de chaque objet de type int en Jarvis.
 * Plusieurs vérifications sont faites ici. Vos nouvelles opérations devraient 
 * être des classes descendantes de cette classe. Opérations déjà implantées:
 *  addition (IntegerPrimitiveAdd)
 *  soustraction (IntegerPrimitiveSubtract)
 *  multiplication (IntegerPrimitiveMultiply)
 *  égalité (IntegerPrimitiveEquals)
 */



public abstract class IntegerPrimitiveOperation extends PrimitiveOperationAtom {
	
	/* 
	 * Le nombre d'argument pour toutes les opérations dérivées de celle-ci sera
	 * de 2 (self + 1 autre). Il devrait donc rester un argument dans la file à cette étape-ci
	 * Ne supporte que des opérations binaires (2 arguments).
	 */
	protected void init() {
		argCount = 1;
	}
	
	//Cette méthode ne fera que la partie spécifique à chaque opération (voir les sous-classes). 
	protected abstract AbstractAtom calculateResult(JarvisInterpreter ji,IntAtom val1, IntAtom val2);
	
	//Cette méthode fait quelques vérifications générales avant d'appeler calculateResult, qui fait l'opération réelle.	
	@Override
	protected AbstractAtom execute(JarvisInterpreter ji,ObjectAtom self) {

		
		//Ici, on peut assumer que l'objet qui a reçu le message (self) est un int et possède donc
		//le champ "value". 
		IntAtom num1 = (IntAtom) self.message("value");
		
		//Le second argument est pris de la file d'arguments. Il peut avoir n'importe quelle forme.
		AbstractAtom arg2=ji.getArg();
		IntAtom num2;
		
		if(arg2 instanceof IntAtom)
		{
			//Si l'argument est de type IntAtom, alors l'opération se fera directement avec lui.
			num2=(IntAtom)arg2;
			
		}
		else
		{
			//Sinon, il faut vérifier si on a obtenu un objet jarvis.
			if(arg2 instanceof ObjectAtom){
				
				//Si c'est un objet de type int, alors il devrait avoir un champ "value".
				AbstractAtom res = ((ObjectAtom) arg2).message("value");
				
				//Si le champ "value" existe et s'il contient bien un intAtom, l'opération se fera avec lui.
				if(res instanceof IntAtom){
					num2 = (IntAtom)res;
				}
				//Si le champ n'existe pas ou qu'il ne contient pas le bon type d'atome, alors on ne peut pas continuer				
				else throw new IllegalArgumentException(makeKey()+", argument 2: object does not contain a \"value\" field of type IntAtom. Class = " + ((ObjectAtom)arg2).findClassName(ji)+", object contents = ["+arg2.makeKey()+"]");
			}
			//Si ce n'est pas un IntAtom ou un ObjectAtom, ça ne peut pas être le bon type d'argument.
			else throw new IllegalArgumentException(makeKey()+", argument 2: wrong atom type " + arg2.getClass().getName()+", value = "+arg2.makeKey());
			
		}
		//Procède finalement au calcul spécifique à chaque primitive concernant 2 int (voir classes filles).	
		return calculateResult(ji, num1, num2);
	}
}
