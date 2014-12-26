package boggle;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WordsPanel extends JPanel{
	
	private ClientGUI gui;
	
	private JLabel wordsLabel;
	private JTextArea selectedWords;
	
	public WordsPanel(ClientGUI gui){
		this.gui = gui;
		
		wordsLabel = new JLabel("Selected Words:");
		wordsLabel.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		selectedWords = new JTextArea();
		
		add(wordsLabel);
		add(selectedWords);
	}

	public JLabel getWordsLabel() {
		return wordsLabel;
	}

	public JTextArea getSelectedWords() {
		return selectedWords;
	}
	
	

}
