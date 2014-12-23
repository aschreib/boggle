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

}
