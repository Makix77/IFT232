package labo6.ui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * Panneau d'affichage pour chacun des utilisateurs du ChatroomLette.
 */

public class UserPanel extends JPanel {

	private static final long serialVersionUID = -8573929208419638428L;

	private JTextArea chatbox;
	private JScrollPane scrollbox;
	private ImagePanel profilePic;
	private ImagePanel genderPic;

	public UserPanel(String name, String pic, String gender) {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel picPanel = new JPanel();
		JPanel genderPanel = new JPanel();
		
		profilePic = new ImagePanel(new ImageIcon(pic).getImage());
		picPanel.add(profilePic);
		picPanel.setMaximumSize(new Dimension(400, 400));
		
		genderPic = new ImagePanel(new ImageIcon(gender).getImage());
		genderPanel.add(genderPic);
		genderPanel.setMaximumSize(new Dimension(100, 100));
		chatbox = new JTextArea();
		chatbox.setFont(new Font("Arial", Font.BOLD, 20));
		chatbox.setText("");
		scrollbox = new JScrollPane(chatbox);
		scrollbox.setMaximumSize(new Dimension(800,400));
		
		
		JLabel title = new JLabel(name);
		title.setFont(new Font("Bookman", Font.BOLD, 40));
		this.add(title);
		this.add(picPanel);
		this.add(genderPanel);

		this.add(scrollbox);

	}

	public void changeImage(String name) {

		profilePic = new ImagePanel(name);
		repaint();

	}
	
	public String getText(){
		return chatbox.getText();
	}
	
	// Ajoute un message dans la boîte de texte et défile vers le bas si
		// nécessaire.
		public void appendMessage(String message) {

			
			chatbox.setText(chatbox.getText()+message+"\n\n");

			JScrollBar vertical = scrollbox.getVerticalScrollBar();
			vertical.setValue(vertical.getMaximum());

		}

}
