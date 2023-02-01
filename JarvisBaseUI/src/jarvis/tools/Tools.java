package jarvis.tools;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.ClosureAtom;
import jarvis.atoms.IntAtom;
import jarvis.interpreter.JarvisInterpreter;

import java.util.ArrayList;
import java.util.Stack;

public class Tools {
	
	public static AbstractAtom buildIntAtom(int val, JarvisInterpreter ji)
	{
		
		
		return new IntAtom(val);
		
		
		
	}

	public static AbstractAtom buildClosureAtom(ArrayList<String> params,
			Stack<String> body, JarvisInterpreter ji) {
		
		
		
		
		
		return new ClosureAtom(params,body);
	}
	
	

}
