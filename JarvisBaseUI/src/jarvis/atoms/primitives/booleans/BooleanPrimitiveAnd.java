package jarvis.atoms.primitives.booleans;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;
import java.util.ArrayList;

public class BooleanPrimitiveAnd extends BooleanPrimitiveOperation {
    public BooleanPrimitiveAnd() {
    }

    public String makeKey() {
        return "BooleanPrimitiveAnd";
    }

    protected AbstractAtom calculateResult(JarvisInterpreter ji, BoolAtom val1, BoolAtom val2) {
        boolean result = val1.toString() == "\\true\\" && val1.toString() == val2.toString();
        ArrayList<AbstractAtom> data = new ArrayList();
        data.add(new BoolAtom(result));
        return new ObjectAtom((ObjectAtom)ji.getEnvironment().get("bool"), data, ji);
    }

    protected AbstractAtom calculateResult(JarvisInterpreter ji, BoolAtom val1) {
        throw new IllegalArgumentException(this.makeKey() + ", argument 2 missing.");
    }
}
