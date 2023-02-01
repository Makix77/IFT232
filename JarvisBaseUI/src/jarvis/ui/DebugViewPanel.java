package jarvis.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jarvis.interpreter.JarvisInterpreter;

public abstract class DebugViewPanel extends JPanel{

	protected JLabel header;
	protected JarvisInterpreter ji;
	public DebugViewPanel(JarvisInterpreter ji,String title) {
		this.ji=ji;
		header = new JLabel(title);
		initUI();
	}
	private static final long serialVersionUID = 4142147500929032381L;
	
	
	protected void initUI() {
		
		header.setFont(MainWindow.HUGEFONT);
		add(header);		
	}

}
