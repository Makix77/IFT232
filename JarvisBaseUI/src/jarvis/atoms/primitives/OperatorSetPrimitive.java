package jarvis.atoms.primitives;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.ListAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;

import java.util.ArrayList;

public class OperatorSetPrimitive extends PrimitiveOperationAtom {
    @Override
    public String makeKey() {
        return "OperatorSetPrimitive";
    }

    @Override
    protected AbstractAtom execute(JarvisInterpreter ji, ObjectAtom self) {
        ListAtom attributes = (ListAtom)self.message("set");

        return null;
    }


    @Override
    protected void init() {

    }
}
