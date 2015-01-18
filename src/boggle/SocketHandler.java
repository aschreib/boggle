package boggle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketHandler extends Thread {

	private Server server;
	private Socket clientSocket;
	private Evaluator evaluator;
	private String[] boggleBoard;

	public SocketHandler(Server server, Socket clientSocket)
			throws FileNotFoundException {

		this.server = server;
		this.clientSocket = clientSocket;
		evaluator = server.getEvaluator();
		boggleBoard = server.getBoard();

	}

	public void run() {

		System.out.println("connected to " + clientSocket.getPort());

		try {

			InputStream in = clientSocket.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));

			String line;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				if (line.equals("start game")) {
					server.incrementNumGamesStarted();
				} else {
					// clients are sending results of game
					evaluate(line); // line has all the words
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendBoardToClient() throws IOException {
		OutputStream out = clientSocket.getOutputStream();
		PrintWriter writer = new PrintWriter(out);
		for (String letter : boggleBoard) {
			writer.println(letter);
			writer.flush();
		}

	}

	public void evaluate(String gameResults) throws IOException {
		// convert string to list
		// send to evaluator
		String[] words = gameResults.split(" ");
		evaluator.receiveList(this, words);
	}

	public void sendResultsToClient(String status, int playerPoints)
			throws IOException {
		OutputStream out = clientSocket.getOutputStream();
		PrintWriter writer = new PrintWriter(out);

		writer.println(status + " ".getBytes() + playerPoints);
		System.out.println(status + "- points: " + playerPoints);
		writer.flush();
		out.close();
		System.out.println("sent results to client");
	}

}
