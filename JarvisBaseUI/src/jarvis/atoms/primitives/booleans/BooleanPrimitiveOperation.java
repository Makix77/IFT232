package jarvis.atoms.primitives.booleans;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.atoms.primitives.PrimitiveOperationAtom;
import jarvis.interpreter.JarvisInterpreter;

public abstract class BooleanPrimitiveOperation extends PrimitiveOperationAtom {
    public BooleanPrimitiveOperation() {
    }

    protected void init() {
        this.argCount = 1;
    }

    protected abstract AbstractAtom calculateResult(JarvisInterpreter var1, BoolAtom var2, BoolAtom var3);

    protected AbstractAtom execute(JarvisInterpreter ji, ObjectAtom self) {
        BoolAtom bool1 = (BoolAtom)self.message("value");
        AbstractAtom arg2 = ji.getArg();
        BoolAtom bool2;
        if (arg2 instanceof BoolAtom) {
            bool2 = (BoolAtom)arg2;
        } else {
            String var10002;
            if (!(arg2 instanceof ObjectAtom)) {
                var10002 = this.makeKey();
                throw new IllegalArgumentException(var10002 + ", argument 2: wrong atom type " + arg2.getClass().getName() + ", value = " + arg2.makeKey());
            }

            AbstractAtom res = ((ObjectAtom)arg2).message("value");
            if (!(res instanceof BoolAtom)) {
                var10002 = this.makeKey();
                throw new IllegalArgumentException(var10002 + ", argument 2: object does not contain a \"value\" field of type BoolAtom. Class = " + ((ObjectAtom)arg2).findClassName(ji) + ", object contents = [" + arg2.makeKey() + "]");
            }

            bool2 = (BoolAtom)res;
        }

        return this.calculateResult(ji, bool1, bool2);
    }
}
