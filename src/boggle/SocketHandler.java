package boggle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocketHandler extends Thread {

	private Server server;
	private Socket clientSocket;
	private Evaluator evaluator;
	private String[] boggleBoard;
	
	public SocketHandler(Server server, Socket clientSocket) throws FileNotFoundException {

		this.server = server;
		this.clientSocket = clientSocket;
		evaluator = server.getEvaluator();
		boggleBoard = server.getBoard();

	}

	public void run() {

		System.out.println("connected to " + clientSocket.getPort());

		InputStream in;
		try {
			in = clientSocket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				if (line.equals("start game")) {
					server.incrementNumGamesStarted();

				} else {// clients are
					// sending
					// results of
					// game
					evaluate(line); // line has all the words
					// checkForWinner();

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void sendBoardToClient() throws IOException {
		System.out.println("send board");
		OutputStream out = clientSocket.getOutputStream();
		PrintWriter writer = new PrintWriter(out);
		for (String letter : boggleBoard) {
			writer.println(letter);
			System.out.println(letter);
			writer.flush();
		}

	}

	public void evaluate(String gameResults) {
		// convert string to list
		// send to evaluator
		String[] words = gameResults.split(" ");
		evaluator.receiveList(this, words);
	}

	/*
	 * public void checkForWinner() throws IOException {
	 * 
	 * int playerNum = 1; int player1Points = 0; int player2Points = 0;
	 * Map<String, Socket> playersValidWords = new HashMap<String, Socket>();
	 * System.out.println("Checking for winner"); for (Socket c : sockets) {
	 * InputStream in = c.getInputStream(); BufferedReader reader = new
	 * BufferedReader(new InputStreamReader(in)); String word; int points; while
	 * ((word = reader.readLine()) != null) { if (dictionary.exists(word)) { if
	 * (playerNum == 2) { if (playersValidWords.containsKey(word)) {
	 * playersValidWords.remove(word); } else { playersValidWords.put(word, c);
	 * points = getWordPoints(word); player2Points += points; } } else {
	 * playersValidWords.put(word, c); } } } points = getWordPoints(word);
	 * playerNum += points; }
	 * 
	 * for (String word : playersValidWords.keySet()) { if
	 * ((playersValidWords.get(word)).equals(sockets.get(0))) { player1Points++;
	 * } }
	 * 
	 * String winner; if (player1Points > player2Points) { winner = "Player1"; }
	 * else { winner = "Player2"; } System.out.println("1 " + player1Points);
	 * System.out.println("2 " + player2Points); System.out.println(winner);
	 * sendResultsToClients(winner, player1Points, player2Points);
	 * 
	 * }
	 */

	/*public void sendResultsToClients(String winner, int player1Points, int player2Points) throws IOException {
		OutputStream out = null;
		for (int i = 0; i < sockets.size(); i++) {
			out = sockets.get(i).getOutputStream();
			out.write("Winner is".getBytes());
			out.write(winner.getBytes());

			if (i % 2 == 0) {// first player (even index)
				out.write("Your point score: ".getBytes());
			} else {
				out.write("Other player's point score: ".getBytes());
			}
			out.write(player1Points);
			if (i % 2 != 0) {// second player (odd index)
				out.write("Your point score: ".getBytes());
			} else {
				out.write("Other player's point score: ".getBytes());
			}
			out.write(player2Points);

		}
		out.close();

	}*/

	/*
	 * public int getWordPoints(String word) { int pointValue = 0; int
	 * wordLength = word.length(); switch (wordLength) { case 3: pointValue = 1;
	 * break; case 4: pointValue = 2; break; case 5: pointValue = 3; break; case
	 * 6: pointValue = 4; break; case 7: pointValue = 5; break; case 8:
	 * pointValue = 11; break; default: pointValue = wordLength * 2; break;
	 * 
	 * } return pointValue; }
	 */

}
