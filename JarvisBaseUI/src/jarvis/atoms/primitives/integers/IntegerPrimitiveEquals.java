package jarvis.atoms.primitives.integers;

import java.util.ArrayList;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.IntAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;

//OPERATIONSPRIMITIVES
/*
* Cette classe implante la partie de l'opération primitive spécifique à l'égalité.
* 
*/

public class IntegerPrimitiveEquals extends IntegerPrimitiveOperation {

	@Override
	public String makeKey() {

		return "IntegerPrimitiveEquals";
	}

	@Override
	protected AbstractAtom calculateResult(JarvisInterpreter ji, IntAtom val1, IntAtom val2) {

		// C'est ici que l'opération réelle a lieu
		boolean result = val1.getValue() == val2.getValue();

		// Ici, construit un objet int manuellement
		// À noter, on retourne un objet de type bool, pas un atome de type
		// bool.
		ArrayList<AbstractAtom> data = new ArrayList<AbstractAtom>();
		data.add(new BoolAtom(result));

		return new ObjectAtom(((ObjectAtom) ji.getEnvironment().get("bool")), data, ji);
	}

}
