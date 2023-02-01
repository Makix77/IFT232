package jarvis.atoms.primitives;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.ListAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;

import java.util.ArrayList;

public class OperatorNewPrimitive extends PrimitiveOperationAtom{

	/*
	 * Triche pour pouvoir avoir des arguments variables.
	 * Le nombre d'arguments nécessaire est déterminé par la taille
	 * de la liste des attributs de la classe qui crée l'instance.
	 */
	protected void init() {
		argCount = -1;
	}
	
	
	//HÉRITAGE	
	/*
	 * Operator new. 
	 * Rien de bien mystérieux: Lorsqu'on fabrique un objet
	 * il suffit de prendre les arguments reçus et de les copier
	 * un à un dans l'objet qu'on fabrique.
	 * Devient plus complexe si on hérite les membres d'une autre classe.
	 * 
	 */
	@Override
	protected AbstractAtom execute(JarvisInterpreter ji,ObjectAtom self) {	
		
		
		//Seule une classe peut faire new. Ramasser de la classe combien d'attributs ça prend.
		
		ListAtom attributes = (ListAtom)self.message("attributes");
		
		ArrayList<AbstractAtom> data = new ArrayList<AbstractAtom>();
		for(int i=0;i<attributes.size();i++)
		{
			if(ji.getArgCount()<=0)
			{
				throw new IllegalArgumentException("Operator new: Bad number of arguments. Expected "+attributes.size()+" got "+i);
			}
			data.add(ji.getArg());
		}		
				
		ObjectAtom res = new ObjectAtom(self, data,ji);		
		
		return res;		
		
	}

	@Override
	public String makeKey() {
		
		return "OperatorNewPrimitive";
	}

}
