package boggle.client;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BoggleBoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int SIZE = 4;

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

	public static int getBoardSize() {
		return SIZE;
	}

	public JButton[][] getBoard() {
		return board;
	}

	public void setBoard(String[] letters) {
		int position = 0;

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				JButton currentButton = board[i][j];
				currentButton.setText(letters[position++]);
				currentButton.setFont(new Font(Font.SERIF, Font.BOLD, 50));
			}
		}

	}

	public ClientGUI getGui() {
		return gui;
	}

	public boolean[][] getAlreadyClicked() {
		return alreadyClicked;
	}

	public void resetClickedList() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				alreadyClicked[i][j] = false;
			}
		}
	}

	public void toggleButtons(boolean enabled) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j].setEnabled(enabled);
			}
		}
	}

}