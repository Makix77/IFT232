package jarvis.atoms;

import jarvis.interpreter.JarvisInterpreter;

public class NullAtom extends AbstractAtom{

	@Override
	public AbstractAtom interpretNoPut(JarvisInterpreter ji) {
		
		return this;
	}

	@Override
	public String makeKey() {
		
		return "null";
	}

}
