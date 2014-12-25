package boggle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SubmitPanel extends JPanel {

	private ClientGUI gui;

	private JTextField wordToSubmit;
	private JButton submitWord;

	public SubmitPanel(ClientGUI gui) {
		this.gui = gui;
		wordToSubmit = new JTextField(20);
		submitWord = new JButton("Submit Word");
		submitWord.setEnabled(false);

		add(wordToSubmit);
		add(submitWord);
	}


	public JButton getSubmitWord() {
		return submitWord;
	}


}
