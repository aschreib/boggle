package boggle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SubmitPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClientGUI gui;

	private JTextField wordToSubmitField;
	private JButton submitWord;

	public SubmitPanel(final ClientGUI gui) {
		this.setGui(gui);
		wordToSubmitField = new JTextField(20);
		submitWord = new JButton("Submit Word");
		//create action listener to submit word and set all buttons clickable
		submitWord.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				//need to submit word
				
				//then set all buttons clickable
				for(int i=0; i<4; i++){
					for (int j=0; j<4; j++){
						gui.getBoggleBoard().getBoard()[i][j].setEnabled(true);
					}
				}		
				// clear textfield
				wordToSubmitField.setText(null);
				
				//then disable submit button again
				submitWord.setEnabled(false);
			}
			
		});
		submitWord.setEnabled(false);

		add(wordToSubmitField);
		add(submitWord);
	}
	
	public JTextField getWordToSubmit(){
		return wordToSubmitField;
	}
	
	public void setWordToSubmit(String word){
		wordToSubmitField.setText(word);
	}


	public JButton getSubmitWord() {
		return submitWord;
	}

	public ClientGUI getGui() {
		return gui;
	}

	public void setGui(ClientGUI gui) {
		this.gui = gui;
	}


}
