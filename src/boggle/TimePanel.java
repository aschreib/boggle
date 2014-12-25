package boggle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class TimePanel extends JPanel{
	
	private ClientGUI gui;
	
	private JButton startGame;
	
	public TimePanel(ClientGUI gui){
		this.gui = gui;
		startGame.addActionListener(new StartGameActionListener());
	}
	

	private class StartGameActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Client c = new Client(gui);// connects to server
			c.createBoard();// gets board from server and then calls
							// gui.createBoard()
			startGame.setEnabled(false);
		}

	}
	
}
