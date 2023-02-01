package jarvis.atoms;

import jarvis.interpreter.JarvisInterpreter;

public class IntAtom extends AbstractAtom {

	private int value;

	public IntAtom(int v) {
		value = v;
	}

	public AbstractAtom interpretNoPut(JarvisInterpreter ji) {

		
		return this;

	}

	public int getValue()
	{
		return value;
	}
		

	@Override
	public String makeKey() {
		
		return ""+value;
	}

	public String toString(){
		return ""+value;
	}
}
