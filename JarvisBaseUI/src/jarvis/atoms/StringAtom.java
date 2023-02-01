package jarvis.atoms;

import jarvis.interpreter.JarvisInterpreter;

public class StringAtom extends AbstractAtom {

	private String value;
	
	

	public StringAtom(String v) {
		value = v;
	}

	public AbstractAtom interpretNoPut(JarvisInterpreter ji) {

		//ji.output(value);

		return this;

	}
	

	@Override
	public String makeKey() {

		return value;
	}

	public String getValue() {
		return value;
	}

	public String toString(){
		return "\""+value+"\"";
	}

}
