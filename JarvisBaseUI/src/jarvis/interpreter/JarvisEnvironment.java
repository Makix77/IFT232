package jarvis.interpreter;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.DictionnaryAtom;

//SOUSLECAPOT
/*
 * Implantation d'un environnement.
 * Un environnement contient tout simplement
 * une table de hashage permettant d'associer
 * des symboles à des valeurs.
 * De plus, les environnements sont enchaînés entre eux.
 * Lorsqu'on évalue une fermeture, on entre dans 
 * un sous-environnement. Une fois la fermeture 
 * évaluée, on revient dans l'environnement précédent.
 * Un environnement enfant a accès à tous les symboles définis
 * dans ses environnements parents. 
 * 
 */

public class JarvisEnvironment {

	private DictionnaryAtom dictionnary;

	private JarvisEnvironment parent;
	private JarvisInterpreter interpreter;

	public JarvisEnvironment(JarvisInterpreter ji) {

		dictionnary = new DictionnaryAtom();
		parent = null;

		interpreter = ji;

	}

	public JarvisEnvironment(JarvisInterpreter ji, JarvisEnvironment p) {

		dictionnary = new DictionnaryAtom();
		parent = p;

		interpreter = ji;

	}

	public void put(String id, AbstractAtom val) {

		dictionnary.put(id, val);
	}

	public AbstractAtom getLocal(String id) {
		return dictionnary.get(id);
	}

	public AbstractAtom get(String id) {

		// recurse parent...
		AbstractAtom res = dictionnary.get(id);
		if (res == null) {
			if (hasParent()) {
				return parent.get(id);
			}

		}
		return res;

	}

	public void print() {
		dictionnary.print(interpreter);

	}

	public boolean hasParent() {

		return parent != null;
	}

	public JarvisEnvironment getParent() {

		return parent;
	}

	public void get(AbstractAtom atom) {

		get(atom.makeKey());

	}
	


	public String reverseLookup(AbstractAtom atom) {

		String result = "_nosymbol_";

		result = dictionnary.reverseLookup(atom);
		
		if (result == null) {
			if (hasParent()) {
				return parent.reverseLookup(atom);
			}

		}		

		return result;
	}

	public void printAll() {

		printAllImpl();

	}
	
	private int printAllImpl() {

		int result = 0;
		if (hasParent()) {
			result = parent.printAllImpl() + 1;
		}
		interpreter.output("\n----------Level " + result + " ------------\n");
		dictionnary.print(interpreter);

		return result;

	}

}
