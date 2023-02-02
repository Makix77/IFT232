package jarvis.atoms.primitives.booleans;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;
import java.util.ArrayList;

public class BooleanPrimitiveOr extends BooleanPrimitiveOperation {
    public BooleanPrimitiveOr() {
    }

    public String makeKey() {
        return "BooleanPrimitiveOr";
    }

    protected AbstractAtom calculateResult(JarvisInterpreter ji, BoolAtom val1, BoolAtom val2) {
        boolean result = val1.toString() == "\\true\\" || val2.toString() == "\\true\\";
        ArrayList<AbstractAtom> data = new ArrayList();
        data.add(new BoolAtom(result));
        return new ObjectAtom((ObjectAtom)ji.getEnvironment().get("bool"), data, ji);
    }

    protected AbstractAtom calculateResult(JarvisInterpreter ji, BoolAtom val1) {
        throw new IllegalArgumentException(this.makeKey() + ", argument 2 missing.");
    }
}
