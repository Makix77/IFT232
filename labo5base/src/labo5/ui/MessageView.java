package labo5.ui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import labo5.Labo5Main;

/*
 * Classe représentant une boîte de texte défilante à laquelle on peut ajouter des lignes de texte.
 */

public class MessageView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollbox;
	private JTextArea messages;
	private String totalText;

	// Ajoute un message dans la boîte de texte et défile vers le bas si
	// nécessaire.
	public void appendMessage(String message) {

		totalText += message + "\n\n";
		messages.setText(totalText);

		JScrollBar vertical = scrollbox.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());

	}

	/*
	 * Génère une boîte de texte défilante avec un titre, puis l'ajoute à l'interface.
	 */
	public MessageView(Labo5Main ui, String name) {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		totalText = "";
		messages = new JTextArea();
		//messages.setMaximumSize(new Dimension(800, 600));
		messages.setFont(new Font("Bookman", Font.BOLD, 14));
		messages.setText(totalText);
		scrollbox = new JScrollPane(messages);
		scrollbox.setMaximumSize(new Dimension(700, 200));
		JLabel title = new JLabel(name);
		title.setFont(new Font("Bookman", Font.BOLD, 16));
		this.add(title);
		this.add(scrollbox);

		ui.addToMainWindow(this);
	}

}
