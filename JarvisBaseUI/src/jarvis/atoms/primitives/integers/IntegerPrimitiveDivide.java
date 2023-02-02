package jarvis.atoms.primitives.integers;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.IntAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;
import java.util.ArrayList;

public class IntegerPrimitiveDivide extends IntegerPrimitiveOperation {
    public IntegerPrimitiveDivide() {
    }

    public String makeKey() {
        return "IntegerPrimitiveDivide";
    }

    protected AbstractAtom calculateResult(JarvisInterpreter ji, IntAtom val1, IntAtom val2) {
        int total = val1.getValue() / val2.getValue();
        ArrayList<AbstractAtom> data = new ArrayList();
        data.add(new IntAtom(total));
        return new ObjectAtom((ObjectAtom)ji.getEnvironment().get("int"), data, ji);
    }
}
