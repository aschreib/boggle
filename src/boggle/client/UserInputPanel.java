package boggle.client;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class UserInputPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ClientGUI gui;

	private TimePanel timePanel;
	private WordsPanel wordsPanel;
	private SubmitPanel submitPanel;

	public UserInputPanel(ClientGUI gui) {
		this.setGui(gui);

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

	public ClientGUI getGui() {
		return gui;
	}

	public void setGui(ClientGUI gui) {
		this.gui = gui;
	}

}
