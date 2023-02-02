package jarvis.atoms.primitives.booleans;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.atoms.primitives.PrimitiveOperationAtom;
import jarvis.interpreter.JarvisInterpreter;

public abstract class BooleanReversePrimitiveOperation extends PrimitiveOperationAtom {
    public BooleanReversePrimitiveOperation() {
    }

    protected void init() {
        this.argCount = 0;
    }

    protected abstract AbstractAtom calculateResult(JarvisInterpreter var1, BoolAtom var2);

    protected AbstractAtom execute(JarvisInterpreter ji, ObjectAtom self) {
        BoolAtom bool1 = (BoolAtom) self.message("value");

        return this.calculateResult(ji, bool1);
    }
}
