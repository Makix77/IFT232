package jarvis.atoms.primitives;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;

public abstract class PrimitiveOperationAtom extends AbstractAtom{
	
	protected int argCount;
	protected abstract AbstractAtom execute(JarvisInterpreter ji,ObjectAtom self);
	
	//OPERATIONSPRIMITIVES
	/*
	 * Gabarit pour les fonctions tricheuses.
	 * La plupart ont un nombre fixe de paramètres, il est donc vérifié ici.
	 * Dans certains cas, on veut laisser cette responsabilité à la sous-classe.
	 * Celle-ci spécifiera donc un nombre de paramètres à -1 dans sa fonction init().
	 * 
	 */	
	
	public PrimitiveOperationAtom()
	{
		init();
	}
	
	protected abstract void init();
	
	@Override
	public AbstractAtom interpretNoPut(JarvisInterpreter ji) {
		
		if (argCount != ji.getArgCount() && argCount!=-1)
		{
			throw new IllegalArgumentException("Primitive "+ji.getEnvironment().reverseLookup(this)+": Bad number of arguments, expected " + argCount
			+ " got " + (ji.getArgCount()));			
		}
		
		//Les fonctions tricheuses sont toujours des méthodes. Elles ne doivent pas être
		//appelées autrement que par l'envoi d'un message à un objet.
		//Ainsi, le symbole self devrait être défini lorsqu'une telle fonction est appelée.
		ObjectAtom self = (ObjectAtom)ji.getEnvironment().get("self");
		
		
		AbstractAtom res =execute(ji,self);
		
		return res;
		
	}

}
