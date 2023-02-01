package jarvis.ui;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jarvis.interpreter.JarvisInterpreter;

public class FileStreamPanel extends DebugViewPanel{

	private static final long serialVersionUID = 6598794548492562022L;
	private JScrollPane scrollbox;
	private JTextArea content;
	private String value;
	
	
	public FileStreamPanel(JarvisInterpreter ji) {
		super(ji, "Fichier");
		
	}

	protected void initUI() {
		super.initUI();

		
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		value="";
		
				
		BufferedReader input = ji.getReader();
		if(ji.hasActiveFile()){
			
		}
		
		content = new JTextArea();
		
		content.setFont(MainWindow.MAINFONT);
		content.setText(value);
		scrollbox = new JScrollPane(content);		
		
		
		add(header);
		add(scrollbox);
				
	}
	
}
