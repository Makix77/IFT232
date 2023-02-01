package jarvis.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import jarvis.interpreter.JarvisInterpreter;

public class MainWindow extends JFrame implements ActionListener {

	public static final Font MAINFONT = new Font("Bookman", Font.BOLD, 14);
	public static final Font BIGFONT = new Font("Bookman", Font.BOLD, 16);
	public static final Font HUGEFONT = new Font("Bookman", Font.BOLD, 18);
	private static final long serialVersionUID = -5000706463784051962L;
	private JPanel background;

	private ArgQueuePanel args;
	private EvalStackPanel stack;
	private InspectPanel inspect;
	private EnvironmentPanel env;
	private FileStreamPanel file;
	private JButton step;
	private JarvisInterpreter ji;
	

	public MainWindow(JarvisInterpreter ji) {
		this.ji = ji;
	}

	public void initUI() {

		step = new JButton("Step");
		step.addActionListener(this);
		step.setPreferredSize(new Dimension(100,20));
		setTitle("Jarvis UI");
		setSize(1000, 800);
		background = new JPanel(new GridLayout(4, 5));
		args = new ArgQueuePanel(ji);
		env = new EnvironmentPanel(ji);
		stack = new EvalStackPanel(ji);
		inspect = new InspectPanel(ji);
		file = new FileStreamPanel(ji);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(step);
		background.add(args);
		background.add(env);
		background.add(stack);
		background.add(inspect);
		background.add(file);
		background.add(buttonPanel);

		add(background);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void refresh(){
		initUI();
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		refresh();
		Object uiLock = ji.getUILock();
		synchronized (uiLock) {

			uiLock.notify();
		}
		
	}

}
