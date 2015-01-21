package boggle.client;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WordsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ClientGUI gui;

	private String wordsLabel;
	private JTextArea selectedWords;

	public WordsPanel(ClientGUI gui) {
		this.setGui(gui);

		wordsLabel = "Selected Words:";
		selectedWords = new JTextArea();
		selectedWords.setFont(new Font(Font.SERIF, Font.BOLD, 12));

		add(selectedWords);
		selectedWords.setEditable(false);
		selectedWords.append(wordsLabel);
	}

	public JTextArea getSelectedWords() {
		return selectedWords;
	}

	public ClientGUI getGui() {
		return gui;
	}

	public void setGui(ClientGUI gui) {
		this.gui = gui;
	}

}
