package jarvis.atoms;

import jarvis.interpreter.JarvisInterpreter;

public class BoolAtom extends AbstractAtom{

	private boolean value;
	
	public BoolAtom(boolean v) {
		value = v;
	}
	
	@Override
	public AbstractAtom interpretNoPut(JarvisInterpreter ji) {		
		
		return this;
	}

	@Override
	public String makeKey() {
		
		return ""+value;
	}
	public String toString(){
		if(value){
			return "\\true\\";
		}
		return "\\false\\";
	}
}
