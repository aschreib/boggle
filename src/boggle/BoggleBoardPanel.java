package boggle;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BoggleBoardPanel extends JPanel {

	public static int SIZE = 4;

	private JButton[][] board;

	public BoggleBoardPanel() {
		board = new JButton[SIZE][SIZE];

		setLayout(new GridLayout(SIZE, SIZE));

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				add(board[i][j] = new JButton());
			}
		}
	}

	public static int getSIZE() {
		return SIZE;
	}

	public JButton[][] getBoard() {
		return board;
	}

	public void setBoard(String[] letters) {
		int position = 0;

		int size = BoggleBoardPanel.getSIZE();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j].setText(letters[position++]);
			}
		}

	}

	// when a button is clicked:
	// 1)get letter and put it in UserInputPanel.SubmitPanel.wordToSubmitField
	// 2)set any non adjacent buttons unclickable, all adjacent buttons should
	// be clickable

}