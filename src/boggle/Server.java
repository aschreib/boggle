package boggle;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	
	//may want to split this up into 2 classes
	
	// must be multi-threaded to be able to connect to 2 clients
	
	private Dictionary dictionary;
	private Client[] Clients; // game is played with exactly 2 clients
	private BoggleDice dice;
	private ServerSocket serverSocket;
	
	//either have constructor or main to open connection - I think we should use a main, added one below
	public Server() throws IOException{
		dictionary = new Dictionary();
		dice = new BoggleDice();
		serverSocket = new ServerSocket(8770);
	}
	
	
	//these 2 methods need to be called one after the other - get the letters from boggle dice and send it to the clients
	//maybe first method should return void and just call the second method inside of it...
	public String[] getBoard(){
		//BoggleDice must be instantiated first!
		return dice.getLetters();
	}
	
	public void sendBoardToClients(){
		
	}
	
	public void checkForWinner(){
		/* has to go through 2 lists and check for duplicates and invalid words.
		 * has to allocate points to players and send back message to players
		 * (another method?) who the winner is
		 */
	}
	
	public static void main(String[]args) throws IOException{
		Server server = new Server();
		
	}
	
}
