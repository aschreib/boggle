package boggle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BoggleButtonListener implements ActionListener {

	public JButton button;
	public String letter;
	public int rowNum;
	public int columnNum;
	public ClientGUI gui;
	public BoggleBoardPanel boardPanel;
	public SubmitPanel submitPanel;
	public StringBuilder stringBuilder;
	private boolean[][] alreadyClicked;

	public BoggleButtonListener(JButton button, int row, int column, BoggleBoardPanel panel) {
		this.button = button;
		this.rowNum = row;
		this.columnNum = column;
		this.letter = button.getText();
		this.boardPanel = panel;
		this.alreadyClicked = panel.getAlreadyClicked();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// set button clicked = true
		alreadyClicked[rowNum][columnNum] = true;

		// word to submit
		submitPanel = boardPanel.getGui().getInputPanel().getSubmitPanel();
		submitPanel.getSubmitWord().setEnabled(true);
		submitPanel.getClear().setEnabled(true);
		stringBuilder = new StringBuilder(submitPanel.getWordToSubmit().getText());
		stringBuilder.append(button.getText());
		submitPanel.setWordToSubmit(stringBuilder.toString());

		// set only those buttons that are adjacent and haven't yet been
		// clicked, clickable
		int size = BoggleBoardPanel.getBoardSize();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				JButton currentButton = boardPanel.getBoard()[i][j];
				if (adjacentRow(i) && adjacentColumn(j) && !alreadyClicked[i][j]) {
					currentButton.setEnabled(true);
				} else {
					currentButton.setEnabled(false);
				}
			}
		}

	}

	public boolean adjacentRow(int thisRow) {
		if (thisRow == rowNum) {
			return true;
		} else if (thisRow == rowNum - 1) {
			return true;
		} else if (thisRow == rowNum + 1) {
			return true;
		} else
			return false;
	}

	public boolean adjacentColumn(int thisColumn) {
		if (thisColumn == columnNum) {
			return true;
		} else if (thisColumn == columnNum - 1) {
			return true;
		} else if (thisColumn == columnNum + 1) {
			return true;
		} else
			return false;
	}

	public StringBuilder getBuildingWord() {
		return stringBuilder;
	}

}
