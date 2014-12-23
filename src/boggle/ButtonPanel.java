package boggle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private ClientGUI gui;

	private JButton startGame;
	private JButton submitWord;

	public ButtonPanel(ClientGUI gui) {
		this.gui = gui;
		startGame = new JButton("Start Game");
		submitWord = new JButton("Submit Word");
		submitWord.setEnabled(false);

		startGame.addActionListener(new StartGameActionListener());

		add(submitWord);
		add(startGame);
	}

	public JButton getStartGame() {
		return startGame;
	}

	public JButton getSubmitWord() {
		return submitWord;
	}

	private class StartGameActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Client c = new Client(gui);// connects to server
			c.createBoard();// gets board from server and then calls
							// gui.createBoard()
			startGame.setEnabled(false);
			submitWord.setEnabled(true);
		}

	}

}
