package boggle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server extends Thread {

	// may want to split this up into 2 classes

	// must be multi-threaded to be able to connect to 2 clients

	private Dictionary dictionary;
	private Socket[] Clients; // game is played with exactly 2 clients
	private BoggleDice dice;
	private ServerSocket serverSocket;

	// either have constructor or main to open connection - I think we should
	// use a main, added one below
	public Server() throws IOException {
		dictionary = new Dictionary();
		dice = new BoggleDice();
		serverSocket = new ServerSocket(8770);

	}

	public void run() {
		int index = 0;

		while (true) {

			try {
				Clients[index++] = serverSocket.accept();
				if (index % 2 == 0) {
					sendBoardToClients();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// these 2 methods need to be called one after the other - get the letters
	// from boggle dice and send it to the clients
	// maybe first method should return void and just call the second method
	// inside of it...
	public String[] getBoard() {
		// BoggleDice must be instantiated first!
		return dice.getLetters();
	}

	public void sendBoardToClients() throws IOException {

		String[] boardLetters = getBoard();

		for (Socket c : Clients) {
			OutputStream out;
			try {
				out = c.getOutputStream();
				PrintWriter writer = new PrintWriter(out);
				for (String letter : boardLetters) {
					writer.print(letter);
					writer.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

	}

	public void checkForWinner() throws IOException {

		int playerNum = 1;
		int player1Points = 0;
		int player2Points = 0;
		HashMap<String, Socket> playersValidWords = new HashMap<String, Socket>();

		for (Socket c : Clients) {
			InputStream in = c.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String word;
			while ((word = reader.readLine()) != null) {
				if (dictionary.exists(word)) {
					if (playerNum == 2) {
						if (playersValidWords.containsKey(word)) {
							playersValidWords.remove(word);
						} else {
							playersValidWords.put(word, c);
							player2Points++;
						}
					} else {
						playersValidWords.put(word, c);
					}
				}
			}
			playerNum++;
		}

		for (String word : playersValidWords.keySet()) {
			if ((playersValidWords.get(word)).equals(Clients[0])) {
				player1Points++;
			}
		}

		String winner;
		if (player1Points > player2Points) {
			winner = "Player1";
		} else {
			winner = "Player2";
		}
		
		sendResultsToClients(winner);

		/*
		 * has to go through 2 lists and check for duplicates and invalid words.
		 * has to allocate points to players and send back message to players
		 * (another method?) who the winner is
		 */
	}

	public void sendResultsToClients(String winner) {

	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();

	}

}