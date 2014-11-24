package boggle;

import java.util.List;
import java.util.Timer;

public class Client {
	
	//this class interacts with the Server - have to include this code in this class
	
	//this structure is just a suggestion - split between Client and ClientGUI - we can change it
	
	private List<String>[] board; //from server
	private List<String>[] words; //chosen by player
	private Timer timer; //don't have to use this class but probably has everything built in that we need, look at java docs
						//maybe this belongs in ClientGUI
	public ClientGUI gui;
	
	public Client(ClientGUI gui){
		this.gui = gui;
	}
	
	public void createBoard(){
		//after gets board from server, calls this method
		gui.createBoard(board);
	}
	
	public void addWord(String word){
		//adds word to list
	}
	
	
	//when time is up sends list to server
	
	

}
