package boggle;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserInputPanel extends JPanel {
	
	private ClientGUI gui;
	private TimePanel timePanel;
	private WordsPanel wordsPanel;
	private SubmitPanel submitPanel;
	

	public UserInputPanel(ClientGUI gui) {
		this.gui = gui;
		
		//wordsLabel = new JLabel("Words you have selected:");
		//wordsLabel.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		timePanel = new TimePanel(gui);
		wordsPanel = new WordsPanel(gui);
		submitPanel = new SubmitPanel(gui);

		setLayout(new BorderLayout());
		
		add(submitPanel, BorderLayout.NORTH);
		add(wordsLabel, BorderLayout.NORTH);
		add(selectedWords, BorderLayout.CENTER);
	}

	public JLabel getWordsLabel() {
		return wordsLabel;
	}

	public JTextArea getSelectedWords() {
		return selectedWords;
	}

	public SubmitPanel getSubmitPanel() {
		return submitPanel;
	}

}
