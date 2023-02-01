package jarvis.ui;

import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jarvis.interpreter.JarvisInterpreter;

public class EvalStackPanel extends DebugViewPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrollbox;
	private JTextArea content;
	private String value;
	
	public EvalStackPanel(JarvisInterpreter ji) {
		super(ji,"Eval Stack");		
	}
	
	protected void initUI() {
		super.initUI();

		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		value="";
		
				
		Stack<String> eval=ji.getEvalStack();
		for(String token:eval){
			value+=token+"\n";
		}
		
		content = new JTextArea();
		
		content.setFont(MainWindow.MAINFONT);
		content.setText(value);
		scrollbox = new JScrollPane(content);		
		
		
		add(header);
		add(scrollbox);
				
	}
}
