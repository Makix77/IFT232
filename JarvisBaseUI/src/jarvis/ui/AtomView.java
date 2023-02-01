package jarvis.ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jarvis.atoms.AbstractAtom;
import jarvis.interpreter.JarvisInterpreter;

public class AtomView extends JPanel{

	private JarvisInterpreter ji;
	private JLabel header;
	private AbstractAtom atom;
	private String value;
	private JTextArea content;
	private JScrollPane scrollbox;
	
	private static final long serialVersionUID = 1L;
	
	
	public AtomView(AbstractAtom a,JarvisInterpreter ji){
		atom=a;
		this.ji=ji;
		initUI();
	}
	
	private void initUI() {
		
		String type=atom.getClass().getName();
		type=type.substring(type.lastIndexOf('.')+1);
		String symbol=ji.getEnvironment().reverseLookup(atom);
		if(symbol==null){
			symbol = "";
		}
		else{
			symbol+=":";
		}
		
		header = new JLabel(symbol+type);
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		value = atom.makeKey();
		content = new JTextArea();
		
		content.setFont(MainWindow.MAINFONT);
		content.setText(value);
		scrollbox = new JScrollPane(content);	
		
		header.setFont(MainWindow.BIGFONT);
		
		
		add(header);
		add(scrollbox);

		
		
		
	}
	
	
	

}
