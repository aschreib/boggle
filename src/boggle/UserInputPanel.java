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
		
		timePanel = new TimePanel(gui);
		wordsPanel = new WordsPanel(gui);
		submitPanel = new SubmitPanel(gui);

		setLayout(new BorderLayout());
		
		add(timePanel, BorderLayout.NORTH);
		add(wordsPanel, BorderLayout.CENTER);
		add(submitPanel, BorderLayout.SOUTH);
	}


	public TimePanel getTimePanel() {
		return timePanel;
	}


	public WordsPanel getWordsPanel() {
		return wordsPanel;
	}


	public SubmitPanel getSubmitPanel() {
		return submitPanel;
	}

	

}
