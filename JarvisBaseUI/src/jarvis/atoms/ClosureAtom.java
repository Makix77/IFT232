package jarvis.atoms;

import jarvis.interpreter.JarvisEnvironment;
import jarvis.interpreter.JarvisInterpreter;

import java.util.ArrayList;
import java.util.Stack;


//SOUSLECAPOT
/*
 * Représentation d'une fermeture.
 * Une fermeture est une suite de commandes qui est évaluée
 * dans un environnement, avec des paramètres.
 * Elle représente une évaluation différée.
 * En effet, l'interpréteur ne fait pas les commandes
 * du corps de la fermeture pendant que vous les entrez.
 * Il les fera plus tard, lorsque la fermeture sera 
 * interprétée avec des arguments associés à ses paramètres. 
 */

public class ClosureAtom extends AbstractAtom {

	private ArrayList<String> params;

	private Stack<String> body;

	private ClosureAtom() {
	}

	public ClosureAtom(ArrayList<String> p, Stack<String> b) {
		params = p;
		body = b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbstractAtom interpretNoPut(JarvisInterpreter ji) {

		/*
		 * Vérification pour le nombre d'arguments.
		 * Une fermeture doit être évaluée avec exactement le bon
		 * nombre d'arguments dans la file.
		 */

		if (params.size() != ji.getArgCount()) {
			throw new IllegalArgumentException("Closure "+ji.getEnvironment().reverseLookup(this)+": Bad number of arguments, expected " + params.size()
					+ " got " + (ji.getArgCount()));
			
		}

		/*
		 * Fabrique un nouvel environnement qui contiendra
		 * les arguments. Les symboles choisis pour les paramètres
		 * se trouvent liés aux valeurs des arguments ci-après.
		 */
		JarvisEnvironment closureEnvironment = new JarvisEnvironment(ji,
				ji.getEnvironment());

		for (String id : params) {
			AbstractAtom val = ji.getArg();	
			closureEnvironment.put(id, val);
		}

		

		/*
		 * Change l'environnement d'interprétation courant 
		 */
		ji.setEnvironment(closureEnvironment);

		/*
		 * Copie les commandes sur la pile de l'intepréteur.
		 * En-dessous doit se trouver une commande spéciale
		 * qui ferme l'environnement dans lequel on vient d'entrer.
		 * 
		 */
		ji.pushEval(CommandAtom.CMD_ENDOFENVIRONMENT);
		Stack<String> temp = (Stack<String>) body.clone();
		while (!temp.isEmpty()) {
			ji.pushEval(temp.pop());
		}
		
		return this;
	}

		
	@Override
	public String makeKey() {

		String p = "(";
		for (String s : params) {
			p += (s + " ");
		}
		p = p.trim();

		
		p = p + "){" + body + "}";

		return p;
	}

	public static ClosureAtom read(JarvisInterpreter ji) {
		ClosureAtom closure = new ClosureAtom();
		closure.params = new ArrayList<String>();
		closure.body = new Stack<String>();

	
		String s = ji.nextInput();
		while (!(s.compareTo(".") == 0)) {

			closure.params.add(s);
			s = ji.nextInput();
		}

	
		s = ji.nextInput();
		int nestingCounter=1;
		while (nestingCounter>0) {

			if(s.compareTo(CommandAtom.CMD_CLOSURE)==0)
			{
				nestingCounter++;
			}
			else if(s.compareTo(CommandAtom.CMD_ENDOFCLOSURE)==0)
			{
				nestingCounter--;
				if(nestingCounter==0)
				{
					continue;
				}
			}
			
			closure.body.push(s);
			s = ji.nextInput();
		}
		

		return closure;
	}
	
	/*
	 * Cas spécial d'interprétation.
	 * Lorsqu'une closure est interprétée, ne doit pas enfiler ce qu'elle
	 * retourne (elle-même) dans la file d'arguments.
	 * Les résultats de l'exécution de ses énoncés sont enfilés par effet de bord
	 * des interprétations découlant de ces énoncés.
	 */
	public void interpret(JarvisInterpreter ji) {
		AbstractAtom res = interpretNoPut(ji);		
		
		if (!(res instanceof ClosureAtom)) {
			ji.putArg(res);
		}
	}
	

}
