package jarvis.atoms;

import jarvis.interpreter.JarvisInterpreter;

import java.util.HashMap;

public class DictionnaryAtom extends AbstractAtom {
	
	
	public static boolean nesting=false;

	private HashMap<String, AbstractAtom> data;

	public DictionnaryAtom() {
		data = new HashMap<String, AbstractAtom>();
	}

	public DictionnaryAtom(HashMap<String, AbstractAtom> values) {
		data = new HashMap<String, AbstractAtom>();
		data.putAll(values);
	}

	@Override
	public AbstractAtom interpretNoPut(JarvisInterpreter ji) {

		
		return this;
	}

	public AbstractAtom get(String key) {
		return data.get(key);
	}

	public void put(String key, AbstractAtom obj) {
		data.put(key, obj);
	}

	
	

	@Override
	public String makeKey() {
		
		if(nesting)
		{
			return "[...]";
		}
		
		String s= "";
		
		for (String ref : data.keySet()) {			
			
				nesting = true;
				s+="[" + ref + "     |     ";
				AbstractAtom atom=data.get(ref);
				if(atom instanceof ClosureAtom)
				{						
					s+=atom+ "]\n";
				}
				else
				{
					s+=atom.makeKey()+ "]\n";
				}
				nesting = false;
			
		}	
		//s+="]";
		
		
		return s;
	}
	
	
	public static DictionnaryAtom read(JarvisInterpreter ji)
	{
		DictionnaryAtom dict = new DictionnaryAtom();
		
		CommandAtom.setDontPut(true);
		//Attention aux dictionnaires mal formés! 
		//Vérifie seulement si la clé est la fin.
		CommandAtom key = ji.readCommandFromInput();
		
		while (!key.isEndOfDictionnary())
		{	
			CommandAtom value = ji.readCommandFromInput();
			
			//La clé peut être fournie par n'importe quel atome.
			
			AbstractAtom keyAtom = key.interpretNoPut(ji);
			
			CommandAtom.setDontInterpret(true);		
			AbstractAtom valueAtom = value.interpretNoPut(ji);
			CommandAtom.setDontInterpret(false);	
			
			dict.data.put(keyAtom.makeKey(),valueAtom);
			key = ji.readCommandFromInput();
		}
		CommandAtom.setDontPut(false);
		
		
		
		return dict;
		
	}

	public String reverseLookup(AbstractAtom atom) {
		
		
		
		for(String symbol:data.keySet())
		{
			if(data.get(symbol) == atom)
			{
				return symbol;
			}
		}
		
		return null;
	}

}
