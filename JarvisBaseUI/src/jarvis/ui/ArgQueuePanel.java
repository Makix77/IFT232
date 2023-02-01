package jarvis.ui;

import java.util.Queue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jarvis.atoms.AbstractAtom;
import jarvis.interpreter.JarvisInterpreter;

public class ArgQueuePanel extends DebugViewPanel{
	
	private static final long serialVersionUID = 1L;

	public ArgQueuePanel(JarvisInterpreter ji) {
		super(ji,"File d'arguments");		
	}


	protected void initUI() {
		super.initUI();
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel,BoxLayout.LINE_AXIS));
		Queue<AbstractAtom> argQueue = ji.getArgQueue();
		int i=1;
		for(AbstractAtom atom:argQueue){
			JPanel itemPanel = new JPanel();
			JLabel num = new JLabel(""+i);
			num.setFont(MainWindow.BIGFONT);
			itemPanel.setLayout(new BoxLayout(itemPanel,BoxLayout.PAGE_AXIS));
			AtomView view = new AtomView(atom,ji);
			itemPanel.add(num);
			itemPanel.add(view);
			listPanel.add(Box.createVerticalGlue());
			listPanel.add(itemPanel);
			i++;
		}	
		add(listPanel);
		
	}

}
