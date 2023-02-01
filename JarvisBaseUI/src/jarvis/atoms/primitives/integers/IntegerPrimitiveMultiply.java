package jarvis.atoms.primitives.integers;

import java.util.ArrayList;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.IntAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;

//OPERATIONSPRIMITIVES
/*
 * Cette classe implante la partie de l'opération primitive spécifique à la multiplication.
 * 
 */
public class IntegerPrimitiveMultiply extends IntegerPrimitiveOperation {

	
	
	

	@Override
	public String makeKey() {

		return "IntegerPrimitiveMultiply";
	}

	@Override
	protected AbstractAtom calculateResult(JarvisInterpreter ji, IntAtom val1, IntAtom val2) {
		
		// C'est ici que l'opération réelle a lieu
		int total = val1.getValue() * val2.getValue();

		// Ici, construit un objet int manuellement
		// À noter, on retourne un objet de type int, pas un atome de type int.
		ArrayList<AbstractAtom> data = new ArrayList<AbstractAtom>();
		data.add(new IntAtom(total));

		return new ObjectAtom(((ObjectAtom) ji.getEnvironment().get("int")), data, ji);
	}

}
