package boggle;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ClientGUI extends JFrame{
	
	private JButton startGame;//action listener on this to instantiate Client and connect to server
	private String tempWord;
	private Client client;
	
	//not sure how we want it to look exactly...
	
	public ClientGUI(){
		
	}
	
	public void createBoard(List<String>[] board){
		//sets the GUI board
	}
	
	//when a letter button is clicked add the letter to tempWord
	//only when enter/button is pressed then call Client.addWord and then clear the tempWord.
	
	//display words to the player that they already chose??
	
	//include code that once a button is clicked the player can only click adjacent buttons
	
	
	public static void main(String[]args){
		ClientGUI window = new ClientGUI();
	}
	

}
