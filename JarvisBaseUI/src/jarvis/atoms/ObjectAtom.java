package jarvis.atoms;

import jarvis.interpreter.JarvisInterpreter;

import java.util.ArrayList;

/*
 * Cette classe implante l'objet de base.
 * L'interpr�teur comprend un objet comme
 * une simple liste de valeurs.
 * L'organisation de ses donn�es est sp�cifi�e
 * par la classe. Celle-ci peut �tre retrouv�e
 * via le lien classReference.
 */
public class ObjectAtom extends AbstractAtom {

	/*
	 * Si vous ajoutez des champs � JarvisClass
	 * ces constantes doivent le refl�ter.
	 * Elles sont utilis�es pour retrouver
	 * les membres d'une classe.
	 * 
	 */
	public static final int ATTRIBUTE_FIELD =0;
	public static final int METHOD_FIELD =1;
	public static final int SUPER_FIELD=2;
	
	/*
	 * R�f�rence � la classe de cet objet.
	 */
	private ObjectAtom classReference;
	private ArrayList<AbstractAtom> values;
	
	//R�f�rence utile pour faire des reverse lookup
	private JarvisInterpreter ji;

	

	// Constructeur d'objet g�n�rique
	// Utilis� comme raccourci par les fonctions tricheuses.
	public ObjectAtom(ObjectAtom theClass, ArrayList<AbstractAtom> vals,JarvisInterpreter ji) {

		classReference = theClass;

		values = new ArrayList<AbstractAtom>();
		values.addAll(vals);
		
		this.ji=ji;
	}
	
	@Override
	public AbstractAtom interpretNoPut(JarvisInterpreter ji) {	
		return this;
	}

	public ObjectAtom getJarvisClass() {
		return classReference;
	}
	
	
	//Cas sp�cial o� le selecteur n'est pas encore encapsul� dans un atome
	//Support� pour all�ger la syntaxe.
	public AbstractAtom message(String selector) {
		
		return message(new StringAtom(selector));
		
	}
	
    //H�RITAGE
	//VARIABLESCLASSE
	/*
	 * Algorithme de gestion des messages.
	 * Ce bout de code a pour responsabilit� de d�terminer si le message
	 * concerne un attribut ou une m�thode. 
	 * Pour implanter l'h�ritage, cet algorithme doit n�cessairement �tre modifi�.
	 */	
	public AbstractAtom message(AbstractAtom selector) {
		//Va chercher les attributs
		ListAtom members = (ListAtom) classReference.getAllAttributes();

		//V�rifie si c'est un attribut 
		int pos = members.find(selector);

		if (pos != -1) {
			return values.get(pos);
		}

		// Va chercher les m�thodes
		AbstractAtom res = findMethod(classReference, selector);

		if (res != null) {
			return res;
		}
		// Rien ne correspond au message
		return new StringAtom("ComprendPas "+ selector);

	}

	public void setClass(ObjectAtom theClass) {
		classReference = theClass;
	}

	
	
	//Surtout utile pour l'affichage dans ce cas-ci...
	@Override
	public String makeKey() {
		String s="";
		int i=0;
		
		s += "\""+ji.getEnvironment().reverseLookup(classReference)+"\":";
		
		for (AbstractAtom atom : values) {
			
			s+=" "+((ListAtom)classReference.values.get(0)).get(i).makeKey()+":";
			if(atom instanceof ClosureAtom)
			{
				s+=atom;
			}
			else
			{
				s+=atom.makeKey();
			}
			
			i++;
		}
		
		return s;
	}	
	
	public String findClassName(JarvisInterpreter ji){
		
		return ji.getEnvironment().reverseLookup(classReference);
		
	}

	public AbstractAtom findMethod(ObjectAtom currentClass, AbstractAtom selector){
		DictionnaryAtom methods = (DictionnaryAtom) currentClass.values.get(METHOD_FIELD);
		AbstractAtom res = methods.get(selector.makeKey());
		if(res == null){
			ListAtom parents = (ListAtom) currentClass.values.get(SUPER_FIELD);
			if(parents.size() == 0)
				return null;
			for(int i = 0; i < parents.size(); i++){
				ObjectAtom parent = (ObjectAtom) parents.get(i);
				return findMethod(parent, selector);
			}
		}
		return res;
	}
	public ListAtom getAllAttributes(){
		ListAtom attributes = (ListAtom)values.get(ATTRIBUTE_FIELD);
		ListAtom parents = (ListAtom)values.get(SUPER_FIELD);
		for(int i = 0; i < parents.size(); i++){
			ObjectAtom parent = (ObjectAtom)parents.get(i);
			ListAtom parentAttributes = (ListAtom)parent.values.get(ATTRIBUTE_FIELD);
			for(int j = 0; j < attributes.size(); j++){
				if(parentAttributes.find(attributes.get(j)) == -1){
					parentAttributes.add(attributes.get(j));
				}
			}
			return parent.getAllAttributes();
		}
		return attributes;
	}
}
