package jarvis.interpreter;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.CommandAtom;
import jarvis.atoms.DictionnaryAtom;
import jarvis.atoms.ListAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.atoms.StringAtom;
import jarvis.atoms.primitives.OperatorNewPrimitive;

import jarvis.atoms.primitives.booleans.BooleanPrimitiveAnd;
import jarvis.atoms.primitives.booleans.BooleanPrimitiveOr;
import jarvis.atoms.primitives.booleans.BooleanPrimitiveReverse;
import jarvis.atoms.primitives.integers.*;
import jarvis.exceptions.UndefinedSymbolException;
import jarvis.ui.MainWindow;

public class JarvisInterpreter {

	
	// R�f�rence � l'environnement courant
	private JarvisEnvironment environment;

	// R�f�rence � l'interface graphique
	private MainWindow ui;

	// D�termine si les valeurs lues par l'interpr�teur sont r�affich�es sur la
	// console
	private boolean echo;
	// D�termine si l'invite de commande doit appara�tre
	private boolean prompt;

	//D�termine si on avance pas � pas au lieu d'ex�cuter directement.
	private boolean step;
	
	//Objet utilis� pour la synchro avec l'interface usager.
	private Object uiLock;
	
	// File contenant les arguments pour le prochain envoi de message.
	// Les fonctions et closures peuvent avoir l'effet de bord de mettre des
	// atomes dans cette file.
	// C'est le m�canisme de retour et de passage de param�tres.
	private Queue<AbstractAtom> argQueue;

	/*
	 * Pile contenant des commandes � �valuer. Utilis�e lorsqu'une closure est
	 * �valu�eou qu'un fichier est charg� avec la commande CMD_LOAD.Les lignes
	 * de texte qui contiennent plus qu'une valeur placent les valeurs suivantes
	 * sur cette pile.Cette pile est doit �tre vide avant de lire des commandes
	 * dans un fichier ou sur la ligne de commande.
	 */
	private Stack<String> evalStack;

	// Indique qu'une errerur est survenue plus t�t (outputError).
	private boolean hasCrashed;

	// Indique que la commande CMD_QUIT a �t� interpr�t�e.
	private boolean hasQuit;
	
	private boolean fromFile;

	// Le flot d'entr�e courant (fichier ou clavier)
	private BufferedReader reader;

	/*
	 * Constructeur. Au d�marrage, un flot d'entr�e doit �tre sp�cifi�. Lorsque
	 * c'est un fichier, l'echo et le prompt devraient �tre mis � false.
	 */
	public JarvisInterpreter(BufferedReader r, boolean e, boolean p,boolean f) {

		fromFile=f;
		echo = e;
		prompt = p;
		reader = r;
		step=false;
		uiLock = new Object();
		init();

	}

	/*
	 * Initialisation de l'interpr�teur.
	 */

	private void init() {

		ui = null;
		hasCrashed = false;
		hasQuit = false;

		// Cr�ation de l'environnement racine. Il contient uniquement les
		// fonctions tricheuses.
		environment = new JarvisEnvironment(this);

		// Ajout des fonctions tricheuses (primitives cod�es en Java).
		addCheaterCode();

		// Cr�ation de l'environnement d'interpr�tation de d�part. Son parent
		// est l'environnement de base.
		environment = new JarvisEnvironment(this, environment);

		// Initialisation de la file d'arguments
		argQueue = new LinkedList<AbstractAtom>();

		// Initialisation de la pile d'�valuation
		evalStack = new Stack<String>();

		// Cr�ation de la classe de base.
		createClassClass();
	}

	// OPERATIONSPRIMITIVES
	// MUTATEUR
	/*
	 * Cette fonction ajoute les primitives du langage dans l'environnement. Les
	 * primitives sont des fonctions qui s'ex�cutent en Java. Elles sont cach�es
	 * dans un environnement parent � celui dans lequel l'interpr�teur d�marre.
	 */
	private void addCheaterCode() {

		environment.put("_integerAddPrimitive", new IntegerPrimitiveAdd());
		environment.put("_integerSubtractPrimitive", new IntegerPrimitiveSubtract());
		environment.put("_integerMultiplyPrimitive", new IntegerPrimitiveMultiply());
		environment.put("_integerDividePrimitive", new IntegerPrimitiveDivide());
		environment.put("_integerLessPrimitive", new IntegerPrimitiveLess());
		environment.put("_integerMorePrimitive", new IntegerPrimitiveMore());
		environment.put("_integerEqualsPrimitive", new IntegerPrimitiveEquals());
		environment.put("_operatorNewPrimitive", new OperatorNewPrimitive());
		environment.put("_booleanReversePrimitive", new BooleanPrimitiveReverse());
		environment.put("_booleanAndPrimitive", new BooleanPrimitiveAnd());
		environment.put("_booleanOrPrimitive", new BooleanPrimitiveOr());

	}

	// H�RITAGE
	// VARIABLESCLASSE
	/*
	 * Cette fonction cr�e la classe des classes. Celle-ci sp�cifie qu'une
	 * classe a deux champs: attributes et methods. Pour cr�er une classe
	 * valide, attributes doit �tre une liste de StringAtom et methods doit �tre
	 * un dictionnaire de paires StringAtom:JarvisAtom.
	 * 
	 * Cette classe ne contient qu'une m�thode: la fonction tricheuse
	 * OperatorNewPrimitive.
	 * 
	 * Cette classe ne devrait pas �tre cod�e en Jarvis. Un changement dans
	 * l'organisation des membres de cette classe implique aussi potentiellement
	 * un changement de l'algorithme d'interpr�tation de messages se trouvant
	 * dans ObjectAtom (JarvisObjet.message( ... )). De plus, cette classe doit
	 * �tre sa propre classe. Le symbole Class ne peut pas �tre r�solu par
	 * l'interpr�teur avant d'exister! La r�f�rence � la classe doit �galement
	 * demeurer un lien direct vers l'objet-classe en question. Si c'�tait
	 * uniquement un symbole � interpr�ter, on pourrait d�finir avec le m�me nom
	 * qu'une classe et ainsi emp�cher les objets de retrouver leur classe.
	 */

	private void createClassClass() {

		/*
		 * Cr�ation de la liste de membres. Un objet instanci� par cette classe
		 * comprend deux membres, la liste des attributs, ainsi que le
		 * dictionnaire des m�thodes. ATTENTION! Rien ne garantit qu'une
		 * instance sera cr��e avec une liste et un dictionnaire comme
		 * arguments! Lisez bien les d�finitions des classes de base
		 * (basictypes.txt) pour avoir des exemples. Si vous modifiez
		 * l'organisation de Class, il faut aussi modifier comment instancier
		 * des classes. Il est possible que vous deviez changer toutes les
		 * d�finitions de classes de basictypes.txt.
		 */
		ListAtom members = new ListAtom();
		members.add(new StringAtom("attributes"));
		members.add(new StringAtom("methods"));

		HashMap<String, AbstractAtom> m = new HashMap<String, AbstractAtom>();
		DictionnaryAtom methods = new DictionnaryAtom(m);

		/*
		 * Cette classe ne contient qu'une m�thode, new. Celle-ci fait usage de
		 * la fonction tricheuse OperatorNewPrimitive. Comme un objet r�sultant
		 * de !(Class new) est instance de Class, il supportera n�cessairement
		 * new aussi (C'est donc une classe). Class supporte new parce qu'elle
		 * est instance d'elle-m�me.
		 */
		methods.put("new", new OperatorNewPrimitive());

		/*
		 * Cr�ation d'un objet qui sera instance de Class Ses donn�es seront la
		 * liste des attributs dictionnaire de m�thodes cr��s plus haut.
		 */
		ArrayList<AbstractAtom> data = new ArrayList<AbstractAtom>();

		data.add(members);
		data.add(methods);

		ObjectAtom ClassClass = new ObjectAtom(null, data, this);

		/*
		 * Cet objet contient la d�finition d'une classe et est instance de
		 * lui-m�me.
		 */
		ClassClass.setClass(ClassClass);

		/*
		 * Ajout de la classe de base dans l'environnement. Elle s'appelle
		 * Class, bien s�r. L'appeler Hippopotamme n'aurait pas �t� tr�s
		 * pratique.
		 */
		environment.put("Class", ClassClass);

	}

	/*
	 * Cette fonction implante une partie de la boucle d'interpr�tation. Elle
	 * lit une commande � partir du flot d'entr�e et l'interpr�te. Les
	 * exceptions lanc�es un peu partout dans l'interpr�teur sont attrapp�es
	 * ici. Lorsqu'une exception survient, l'interpr�teur tente de red�marrer
	 * afin que l'utilisateur puisse avoir un peu de contr�le apr�s l'erreur.
	 * Pour d�terminer o� se trouve une erreur dans du code Jarvis, il faut
	 * ajouter des points d'arr�t dans le fichier de code avec la commande
	 * CMD_DEBUG. L'�tat de l'environnement n'est pas n�cessairement correct
	 * apr�s qu'une erreur soit survenue.
	 */

	public void run() {

		CommandAtom cmd;
		prompt();
		try {
			cmd = readCommandFromInput();
			cmd.interpret(this);
		} catch (UndefinedSymbolException e) {
			outputErrorNoCrash(e.getMessage());
			reset();
		} catch (IllegalArgumentException e) {
			outputErrorNoCrash(e.getMessage());
			reset();
		} catch (NoSuchElementException e) {
			outputErrorNoCrash(e.getMessage());
			reset();
		}

		if (getArgCount() > 0) {
			output(peekArg().makeKey());
		}

		while (!hasQuit()) {

			prompt();

			if(step){
				synchronized(uiLock){
					try {
						uiLock.wait();
					} catch (InterruptedException e) {}
				}
			}
			try {
				cmd = readCommandFromInput();
				cmd.interpret(this);
			} catch (UndefinedSymbolException e) {
				outputErrorNoCrash(e.getMessage());
				reset();
			} catch (IllegalArgumentException e) {
				outputErrorNoCrash(e.getMessage());
				reset();
			} catch (NoSuchElementException e) {
				outputErrorNoCrash(e.getMessage());
				reset();
			}

			if (hasCrashed) {

				outputError("Stopping due to earlier error");
				hasQuit = true;
			}

		}

	}

	/*
	 * Tentative de red�marrage. Vide la liste d'arguments et la pile
	 * d'�valuation. Vous pouvez faire quelque-chose de plus intelligent ici.
	 */
	private void reset() {

		argQueue = new LinkedList<AbstractAtom>();
		evalStack = new Stack<String>();
	}

	/*
	 * Cas sp�cial qui survient lorsque l'interpr�teur d�pile
	 * CMD_ENDOFENVIRONMENT. Cette commande se trouve empil�e lorsqu'on entre
	 * dans une closure et qu'on cr�e son environnement.
	 */
	public void endOfEnvironment() {
		if (environment.hasParent()) {

			environment = environment.getParent();
		} else {
			outputErrorNoCrash("Cannot close global environment!");
		}
	}

	// SOUSLECAPOT
	/*
	 * Interpr�tation de message. Plusieurs trucs pas parfaitement propres ici
	 * Ceci est la partie de l'interpr�teur qui sert � fabriquer des messages et
	 * les envoyer aux objets.
	 */

	public void message() {

		// Les atomes li�s � des symboles ne doivent pas �tre interpr�t�s
		// r�cursivement ici
		CommandAtom.setDontInterpret(true);

		CommandAtom cmd;
		CommandAtom objectName;

		ObjectAtom obj;

		// Les symboles ind�finis sont d�tect�s localement au cas o� un
		// traitement sp�cial
		// devrait �tre fait dans le futur
		CommandAtom.setAcceptUndefined(true);

		// Lecture de la premi�re valeur: le destinataire du message.
		// Celui-ci peut se trouver dans la liste d'arguments.
		// Pour l'en r�cup�rer, il faut utiliser la commande CMD_ARG
		cmd = readCommandFromInput();
		AbstractAtom res = cmd.interpretNoPut(this);
		try {
			obj = (ObjectAtom) res;
		} catch (ClassCastException e) {

			throw new IllegalArgumentException(
					cmd.makeKey() + " is not an object. Type:" + res.getClass() + " , Value: " + res.makeKey());
		}

		if (res.isUndefined()) {
			throw new UndefinedSymbolException(cmd.makeKey() + ": symbole ind�fini");

		}

		objectName = cmd;
		// Lecture de la seconde valeur: le s�lecteur.
		// Celui-ci peut se trouver dans la liste d'arguments.
		// Pour l'en r�cup�rer, il faut utiliser la commande CMD_ARG
		cmd = readCommandFromInput();
		AbstractAtom selector = cmd.interpretNoPut(this);
		if (selector.isUndefined()) {
			selector = new StringAtom(selector.makeKey());
		}

		CommandAtom.setAcceptUndefined(false);

		// L'objet qui recoit le message poss�de son propre environnement.
		// Dans cet environnement, on peut envoyer des messages � self,
		// qui est un symbole r�f�ren�ant l'objet courant.
		JarvisEnvironment objectEnvironment = new JarvisEnvironment(this, getEnvironment());

		// Le symbole auquel est li� l'objet courant est self.
		objectEnvironment.put("self", obj);
		setEnvironment(objectEnvironment);

		// Lecture des arguments directement
		// Vous pouvez inclure des valeurs atomiques ou des symboles d�finis
		// comme arguments. Les arguments restants seront r�cup�r�s dans la
		// file d'arguments. Attention � l'ordre! Ceux dans la parenth�se
		// passent en priorit�.
		cmd = readCommandFromInput();
		while (!cmd.isEndOfList()) {

			cmd.interpret(this);
			cmd = readCommandFromInput();
		}
		CommandAtom.setDontInterpret(false);

		// Empile une commande qui d�truira l'environnement de l'objet apr�s
		// l'�valuation du message.
		pushEval(CommandAtom.CMD_ENDOFENVIRONMENT);
		// Interpr�te le message, obtient le membre correspondant.

		res = obj.message(selector);

		// Interpr�tation du r�sultat. Si c'est une m�thode, elle sera
		// ex�cut�e dans l'environnement de self, avec les arguments
		// pr�sentement dans la file.
		res.interpret(this);

		// Message interpr�t�. On sort de l'environnement de l'objet.
		// BUG: Cette ligne a �t� retir�e et remplac�e par l'empilement d'une
		// commande
		// CMD_ENDOFENVIRONMENT au moment opportun. Emp�chait l'acc�s aux
		// param�tres d'une m�thode.
		// setEnvironment(environment.getParent());

		// V�rification du retour.
		// D�commentez ces lignes si vous voulez un affichage de la valeur en
		// t�te
		// de file apr�s chaque message.
		// Il est mieux d'ins�rer une commande CMD_ARGS directement dans votre
		// code Jarvis pour voir la file au complet.
		// Un message d'avertissement demeure si vous obtenez "ComprendPas".
		// L'interpr�teur crash �galement.

		if (argQueue.size() > 0) {

			AbstractAtom resMsg = peekArgTail();
			if (resMsg instanceof StringAtom) {

				StringAtom resStr = (StringAtom) resMsg;
				if (resStr.getValue().startsWith("ComprendPas")) {
					throw new IllegalArgumentException("Object \"" + objectName.makeKey() + "\" of class "
							+ obj.findClassName(this) + " does not understand message \"" + selector.makeKey() + "\"");
				}
			}
		}
		/*
		 * if (argQueue.size() > 0) { print("Retour:");
		 * peekArg().interpretNoPut(this); }
		 */

	}

	// Appel� lorsque la commande CMD_QUIT est interpr�t�e.
	public void quit() {
		hasQuit = true;
	}

	// D�compte du nombre d'arguments dans la file d'arguments.
	public int getArgCount() {
		return argQueue.size();
	}

	// D�file l'argument en t�te de file.
	public AbstractAtom getArg() {
		return argQueue.remove();
	}

	// Enfile un argument. Utilis� lorsqu'un atome est interpr�t� normalement
	// (JarvisAtom.interpret)
	public void putArg(AbstractAtom obj) {
		argQueue.add(obj);
	}

	// Empile des commandes � �valuer.
	public void pushEval(String expression) {
		evalStack.push(expression);
	}

	// Envoie un message d'erreur � la console sans arr�ter l'interpr�teur.
	private void outputErrorNoCrash(Object value) {
		System.err.println(value);
		println("");

	}

	// Permet de voir l'argument en t�te de file.
	public AbstractAtom peekArg() {

		return argQueue.peek();
	}

	// Permet de voir l'argument en queue de file.
	public AbstractAtom peekArgTail() {

		return ((LinkedList<AbstractAtom>) argQueue).getLast();

	}

	// Utilis�e pour obtenir la prochaine commande � interpr�ter.
	public CommandAtom readCommandFromInput() {
		CommandAtom cmd = new CommandAtom(nextInput());
		return cmd;
	}

	// Affichage de la file d'arguments. Appel� par la commande CMD_ARGS.
	public void printArgs() {
		print("Args: (");
		for (AbstractAtom arg : argQueue) {
			print(arg + ",");
		}
		println(")");
	}

	// Lorsqu'un nouveau symbole est cr�� avec la commande CMD_REF, il faut lier
	// celui-ci � une valeur.
	public AbstractAtom getValue() {

		// Obtient d'abord ses valeurs de la file d'arguments
		if (argQueue.size() > 0) {

			return getArg();
		}

		// Si celle-ci est vide, assume que la valeur sera la prochaine
		// commande.
		return readAtomFromInput();
	}

	// Affichage de l'environnement courant. Appel� par la commande CMD_ENV
	public void printEnvironment() {

		environment.print();
	}

	// Raccourci d'affichage.
	public static void print(Object obj) {

		System.out.print(obj);

	}

	// Raccourci d'affichage.
	public static void println(Object obj) {

		System.out.println(obj);
	}

	/*
	 * Horrible fonction qui sert � lire plusieurs commandes sur la m�me ligne.
	 * Le seul semblant de d�cortication de syntaxe se trouve ici, ainsi
	 * qu'�parpill� dans les diverses fonctions de CommandAtom. Ne regardez pas
	 * �a de trop pr�s... �a fait mal aux yeux.
	 */
	public String putTokensOnStack(String line) {
		final String delims = " ()[]{}";
		Stack<String> tokens = new Stack<String>();

		StringTokenizer tokenizer = new StringTokenizer(line, delims, true);

		while (tokenizer.hasMoreTokens()) {
			tokens.push(tokenizer.nextToken());
		}

		String previousToken = "";
		String currentToken = "";
		boolean glue = false;
		while (!tokens.isEmpty()) {
			currentToken = tokens.pop();
			currentToken = currentToken.trim();
			if (currentToken.compareTo("(") == 0 || currentToken.compareTo("{") == 0) {
				previousToken = currentToken;
				glue = true;
				continue;
			} else if (currentToken.compareTo("") == 0) {
				continue;
			}

			if (glue) {
				glue = false;
				if (currentToken.compareTo("") == 0) {
					evalStack.push(previousToken);
				} else {
					currentToken += previousToken;
					evalStack.push(currentToken);
				}
				previousToken = "";
			} else {
				evalStack.push(currentToken);
			}

		}
		if (glue) {
			evalStack.push(previousToken);
		}

		if (!evalStack.isEmpty()) {
			return evalStack.pop();
		} else {
			return "";
		}

	}

	/*
	 * Fonction appel�e pour obtenir la prochaine commande sous forme de string.
	 * Regarde d'abord dans la pile d'�valuation.
	 */
	public String nextInput() {

		String s = "";
		if (evalStack.size() > 0) {
			s = evalStack.pop();
			if (s.length() == 0) {
				return nextInput();
			} else if (s.startsWith("#")) {
				return nextInput();
			}
			return s;

		} else {
			return readLine();
		}

	}

	/*
	 * Patch pour lire correctement des fichiers avec la commande CMD_LOAD.
	 * Devrait �tre utilis�e par readLine, mais la combinaison est encore buggy.
	 */
	public String readSignificantLine(BufferedReader r) {
		String line = "";
		while (line.length() == 0 || line.startsWith("#")) {
			try {
				line = r.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line == null) {
				return null;
			}
			line = line.trim();
		}

		return line;
	}

	/*
	 * �pouvantable fonction servant � lire la prochaine commande sous forme de
	 * string, lorsque la pile d'�valuation est vide. Un peu trop de singeries
	 * pour donner le bon comportement � l'interpr�teur. Ne regardez pas de trop
	 * pr�s... �a fait saigner les yeux.
	 */
	public String readLine() {
		String s = "";

		try {
			s = reader.readLine();

			if (s == null) {
				println("End of file. Going to console...");
				prompt = true;
				prompt();
				reader = new BufferedReader(new InputStreamReader(System.in));
				fromFile=false;
				echo = false;
				return nextInput();
			} else {
				s = s.trim();

				if (s.length() == 0) {
					return nextInput();
				} else if (s.startsWith("#")) {
					return nextInput();
				} else {
					s = putTokensOnStack(s);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (echo) {
			println(s);
		}
		return s;
	}

	public void output(Object value) {
		println(value);

	}

	public void outputDebug(Object value) {
		print("#debug# | ");
		output(value);

	}

	public JarvisEnvironment getEnvironment() {
		return environment;
	}

	public void outputError(Object value) {

		hasCrashed = true;
		outputErrorNoCrash(value);
	}

	public void setEnvironment(JarvisEnvironment newEnvironment) {

		environment = newEnvironment;
	}

	/*
	 * Fonction utile pour d�bugger. Affiche le contenu de la pile d'�valuation.
	 * Attention, un peu cryptique � lire.
	 */

	public void printEvalStack() {

		println("----------Eval stack------------");

		for (String s : evalStack) {

			println(s);

		}
		println("----------Eval stack------------");

	}

	public boolean hasQuit() {

		return hasQuit;
	}

	public void setReader(BufferedReader r) {
		reader = r;

	}

	public void setEcho(boolean e) {
		echo = e;

	}

	public void clearArgs() {
		argQueue.clear();

	}

	public AbstractAtom readAtomFromInput() {
		CommandAtom cmd = readCommandFromInput();
		AbstractAtom atom = cmd.interpretNoPut(this);
		return atom;
	}

	public void prompt() {
		if (prompt && evalStack.isEmpty()) {
			print(">");
		}
	}

	public void setPrompt(boolean p) {
		prompt = p;
	}

	public void printAllEnvironment() {
		environment.printAll();

	}

	public void startUI() {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (ui == null) {
					ui = new MainWindow(JarvisInterpreter.this);
				}
				ui.initUI();
				ui.setVisible(true);
			}
		});
		step=true;

	}

	public Queue<AbstractAtom> getArgQueue() {

		return argQueue;
	}

	public Stack<String> getEvalStack() {

		return evalStack;
	}
	
	public Object getUILock(){
		return uiLock;
	}
	public BufferedReader getReader(){
		return reader;
	}

	public boolean hasActiveFile() {
		
		return fromFile;
	}
}