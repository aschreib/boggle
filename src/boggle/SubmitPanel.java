package boggle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SubmitPanel extends JPanel {

	private ClientGUI gui;

	private JTextField wordToSubmitField;
	private JButton submitWord;

	public SubmitPanel(ClientGUI gui) {
		this.gui = gui;
		wordToSubmitField = new JTextField(20);
		submitWord = new JButton("Submit Word");
		submitWord.setEnabled(false);

		add(wordToSubmitField);
		add(submitWord);
	}
	
	public JTextField getWordToSubmit(){
		return wordToSubmitField;
	}


	public JButton getSubmitWord() {
		return submitWord;
	}


}
