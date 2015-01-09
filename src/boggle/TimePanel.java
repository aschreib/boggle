package boggle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClientGUI gui;

	private JLabel timerLabel;
	private JButton startGame;

	private int minute;
	private int second;

	public TimePanel(ClientGUI gui) {
		this.gui = gui;
		minute = 3;
		second = 59;

		timerLabel = new JLabel("Time Remaining: ");
		setTimerLabelText();
		startGame = new JButton("Start Game");

		startGame.addActionListener(new StartGameActionListener());

		add(timerLabel);
		add(startGame);
	}

	public JLabel getTimerLabel() {
		return timerLabel;
	}

	public void setTimerLabelText() {
		StringBuilder timeText = new StringBuilder(timerLabel.getText());
		timeText.append(getMinute() + ":" + getSecond());
		timerLabel.setText(timeText.toString());
	}

	public JButton getStartGame() {
		return startGame;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	private class StartGameActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				gui.getClient().startGame("start game\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			startGame.setEnabled(false);
		}

	}

}