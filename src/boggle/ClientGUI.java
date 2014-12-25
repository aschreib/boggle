package boggle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class ClientGUI extends JFrame {

	private JSplitPane splitPane;
	private BoggleBoardPanel boggleBoard;
	private UserInputPanel wordsPanel;

	private String tempWord;
	private Client client;

	public ClientGUI() {
		setTitle("Boggle");
		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boggleBoard = new BoggleBoardPanel(),
				wordsPanel = new UserInputPanel(this));
		splitPane.setDividerLocation(getWidth() / 2);
		splitPane.setEnabled(false);
		add(splitPane);

	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}

	public BoggleBoardPanel getBoggleBoard() {
		return boggleBoard;
	}

	public void setBoggleBoard(BoggleBoardPanel boggleBoard) {
		this.boggleBoard = boggleBoard;
	}

	public UserInputPanel getWordsPanel() {
		return wordsPanel;
	}

	public void setWordsPanel(UserInputPanel wordsPanel) {
		this.wordsPanel = wordsPanel;
	}

	public String getTempWord() {
		return tempWord;
	}

	public void setTempWord(String tempWord) {
		this.tempWord = tempWord;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void createBoard(String[] board) {

		int position = 0;

		JButton[][] boggleButtons = boggleBoard.getBoard();
		int size = BoggleBoardPanel.getSIZE();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				boggleButtons[i][j].setText(board[position++]);
			}
		}
	}

	// when a letter button is clicked add the letter to tempWord
	// only when enter/button is pressed then call Client.addWord and then clear
	// the tempWord.

	// display words to the player that they already chose??

	// include code that once a button is clicked the player can only click
	// adjacent buttons

	public static void main(String[] args) {
		ClientGUI window = new ClientGUI();
		window.setVisible(true);
	}

}
