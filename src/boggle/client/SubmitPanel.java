package boggle.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SubmitPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ClientGUI gui;
	private JButton submitWord;
	private JButton clear;
	private JTextField wordToSubmitField;
	private JTextArea textArea;
	private ArrayList<String> wordList;

	public SubmitPanel(final ClientGUI gui) {
		this.setGui(gui);

		wordList = new ArrayList<String>();
		wordToSubmitField = new JTextField(20);
		wordToSubmitField.setEditable(false);

		clear = new JButton("Clear");
		clear.addActionListener(new ClearActionListener(this));

		submitWord = new JButton("Submit Word");
		// create action listener to submit word and set all buttons clickable
		submitWord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// display word in JTextArea
				textArea = gui.getInputPanel().getWordsPanel().getSelectedWords();
				textArea.append("\n" + wordToSubmitField.getText());

				// need to submit word
				wordList.add(wordToSubmitField.getText());

				reset();
			}

		});

		submitWord.setEnabled(false);
		clear.setEnabled(false);

		add(wordToSubmitField);
		add(submitWord);
		add(clear);
	}

	public JTextField getWordToSubmit() {
		return wordToSubmitField;
	}

	public void setWordToSubmit(String word) {
		wordToSubmitField.setText(word);
	}

	public JButton getSubmitWord() {
		return submitWord;
	}

	public JButton getClear() {
		return clear;
	}

	public ClientGUI getGui() {
		return gui;
	}

	public void setGui(ClientGUI gui) {
		this.gui = gui;
	}

	public ArrayList<String> getWordList() {
		return wordList;
	}

	public void setWordList(ArrayList<String> wordList) {
		this.wordList = wordList;
	}

	public void reset() {
		wordToSubmitField.setText("");
		BoggleBoardPanel boggleBoard = gui.getBoggleBoard();
		boggleBoard.toggleButtons(true);
		boggleBoard.resetClickedList();
		clear.setEnabled(false);
		submitWord.setEnabled(false);
	}

	private class ClearActionListener implements ActionListener {

		private SubmitPanel submitPanel;

		public ClearActionListener(SubmitPanel submitPanel) {
			this.submitPanel = submitPanel;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

			submitPanel.reset();
		}

	}

}
