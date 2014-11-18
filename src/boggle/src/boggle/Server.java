package boggle;

import java.net.ServerSocket;

public class Server {
	
	// must be multi-threaded to be able to connect to 2 clients
	
	private Dictionary dictionary;
	private Client[] Clients; // game is played with exactly 2 clients
	private BoggleDice dice;
	private ServerSocket serverSocket;
	
	//either have constructor or main to open connection
	
	
}
