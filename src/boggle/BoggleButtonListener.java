package boggle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class BoggleButtonListener implements ActionListener {

	public JButton button;
	public String letter;
	public int rowNum;
	public int columnNum;
	public ClientGUI gui;
	public BoggleBoardPanel boardPanel;
	public SubmitPanel submitPanel;
	public JTextField buildingWord;
	public StringBuilder stringBuilder;

	public BoggleButtonListener(JButton button, int row, int column, BoggleBoardPanel panel) {
		this.button = button;
		this.rowNum = row;
		this.columnNum = column;
		this.letter = button.getText();
		this.boardPanel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//append letter to buildingWord
		gui = boardPanel.getGui();
		submitPanel = gui.getWordsPanel().getSubmitPanel();
		submitPanel.getSubmitWord().setEnabled(true);
		buildingWord = submitPanel.getWordToSubmit();
		stringBuilder = new StringBuilder(buildingWord.getText());
		stringBuilder.append(button.getText());
		submitPanel.setWordToSubmit(stringBuilder.toString());
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				JButton currentButton = boardPanel.getBoard()[i][j];
				if (adjacentRow(i) && adjacentColumn(j)){
					currentButton.setEnabled(true);
				}
				else{
					currentButton.setEnabled(false);
				}
			}
		}
	}
	
	public boolean adjacentRow(int thisRow){
		if(thisRow == rowNum){
			return true;
		}
		else if(thisRow == rowNum-1){
			return true;
		}
		else if(thisRow == rowNum+1){
			return true;
		}
		else return false;
	}
	
	public boolean adjacentColumn(int thisColumn){
		if(thisColumn == columnNum){
			return true;
		}
		else if(thisColumn == columnNum-1){
			return true;
		}
		else if(thisColumn == columnNum+1){
			return true;
		}
		else return false;
	}
	
	public StringBuilder getBuildingWord() {
		return stringBuilder;
	}

}
