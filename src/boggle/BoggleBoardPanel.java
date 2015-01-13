package boggle;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BoggleBoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static int SIZE = 4;

	private ClientGUI gui;
	private JButton[][] board;
	private boolean[][] alreadyClicked;

	public BoggleBoardPanel(ClientGUI gui) {
		this.gui = gui;
		board = new JButton[SIZE][SIZE];
		alreadyClicked = new boolean[SIZE][SIZE];

		setLayout(new GridLayout(SIZE, SIZE));

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				add(board[i][j] = new JButton());
				board[i][j].addActionListener(new BoggleButtonListener(board[i][j], i, j, this));
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

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j].setText(letters[position++]);
			}
		}

	}

	public ClientGUI getGui() {
		return gui;
	}

	public void setGui(ClientGUI gui) {
		this.gui = gui;
	}

	public boolean[][] getAlreadyClicked() {
		return alreadyClicked;
	}

	public void setAlreadyClicked(boolean[][] alreadyClicked) {
		this.alreadyClicked = alreadyClicked;
	}

	public void resetClickedList() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				alreadyClicked[i][j] = false;
			}
		}
	}

	/*
	 * public void disableAll() { for (int i = 0; i < SIZE; i++) { for (int j =
	 * 0; j < SIZE; j++) { board[i][j].setEnabled(false); } } }
	 */

	public void toggleButtons(boolean enabled) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j].setEnabled(enabled);
			}
		}
	}

	// when a button is clicked:
	// 1)get letter and put it in UserInputPanel.SubmitPanel.wordToSubmitField
	// 2)set any non adjacent buttons unclickable, all adjacent buttons should
	// be clickable

}