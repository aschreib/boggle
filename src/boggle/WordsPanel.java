package boggle;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WordsPanel extends JPanel {

	private JLabel words;
	private JTextArea selectedWords;
	private ButtonPanel buttonPanel;
	private ClientGUI gui;

	public WordsPanel(ClientGUI gui) {
		this.gui = gui;
		words = new JLabel("Words you have selected:");
		words.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		selectedWords = new JTextArea();
		selectedWords.setEditable(false);
		buttonPanel = new ButtonPanel(gui);

		setLayout(new BorderLayout());

		add(words, BorderLayout.NORTH);
		add(selectedWords, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public JLabel getWords() {
		return words;
	}

	public JTextArea getSelectedWords() {
		return selectedWords;
	}

	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

}
