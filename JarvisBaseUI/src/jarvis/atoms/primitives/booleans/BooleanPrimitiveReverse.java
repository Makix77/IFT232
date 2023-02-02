package jarvis.atoms.primitives.booleans;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;
import java.util.ArrayList;

public class BooleanPrimitiveReverse extends BooleanReversePrimitiveOperation {
    public BooleanPrimitiveReverse() {
    }

    public String makeKey() {
        return "BooleanPrimitiveReverse";
    }

    protected AbstractAtom calculateResult(JarvisInterpreter ji, BoolAtom val1) {
        boolean result = val1.toString() != "\\true\\";
        ArrayList<AbstractAtom> data = new ArrayList();
        data.add(new BoolAtom(result));
        return new ObjectAtom((ObjectAtom)ji.getEnvironment().get("bool"), data, ji);
    }
}
