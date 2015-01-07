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
import java.util.Map;

public class Server extends Thread {

	// may want to split this up into 2 classes

	// must be multi-threaded to be able to connect to 2 clients

	private Dictionary dictionary;
	private Socket[] clients; // game is played with exactly 2 clients
	private BoggleDice dice;
	private ServerSocket serverSocket;

	// either have constructor or main to open connection - I think we should
	// use a main, added one below
	public Server() throws IOException {
		dictionary = new Dictionary();
		dice = new BoggleDice();
		serverSocket = new ServerSocket(8080);

	}

	public void run() {
		int index = 0;
		Socket clientSocket;

		while (true) {

			try {
				clientSocket = serverSocket.accept();

				InputStream in = clientSocket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String word;
				while ((word = reader.readLine()) != null) {
					if (word.equals("start game")) {
						clients[index++] = clientSocket;
						if (index % 2 == 0) {
							// commented if out to test on one
							sendBoardToClients();
						}

					} else if (word.equals("game results")) {// clients are
																// sending
																// results of
																// game
						checkForWinner();
					}
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

		for (Socket c : clients) {
			OutputStream out;
			try {
				out = c.getOutputStream();
				PrintWriter writer = new PrintWriter(out);
				for (String letter : boardLetters) {
					writer.println(letter);
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
		Map<String, Socket> playersValidWords = new HashMap<String, Socket>();

		for (Socket c : clients) {
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
			if ((playersValidWords.get(word)).equals(clients[0])) {
				player1Points++;
			}
		}

		String winner;
		if (player1Points > player2Points) {
			winner = "Player1";
		} else {
			winner = "Player2";
		}

		sendResultsToClients(winner, player1Points, player2Points);

		/*
		 * has to go through 2 lists and check for duplicates and invalid words.
		 * has to allocate points to players and send back message to players
		 * (another method?) who the winner is
		 */
	}

	public void sendResultsToClients(String winner, int player1Points, int player2Points) throws IOException {
		OutputStream out = null;
		for (int i = 0; i < clients.length; i++) {
			out = clients[i].getOutputStream();
			out.write("Winner is".getBytes());
			out.write(winner.getBytes());

			if (i == 0) {
				out.write("Your point score: ".getBytes());
			} else {
				out.write("Other player's point score: ".getBytes());
			}
			out.write(player1Points);
			if (i == 1) {
				out.write("Your point score: ".getBytes());
			} else {
				out.write("Other player's point score: ".getBytes());
			}
			out.write(player2Points);

		}
		out.close();

	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public Socket[] getClients() {
		return clients;
	}

	public BoggleDice getDice() {
		return dice;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.run();

	}

}
