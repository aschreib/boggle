package boggle.server;

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

	public SocketHandler(Server server, Socket clientSocket) throws FileNotFoundException {

		this.server = server;
		this.clientSocket = clientSocket;
		evaluator = server.getEvaluator();
		boggleBoard = server.getBoard();

	}

	public void run() {

		try {

			InputStream in = clientSocket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;

			while ((line = reader.readLine()) != null) {
				if (line.equals("start game")) {
					server.incrementNumGamesStarted();
				} else {
					// clients are sending results of game
					evaluate(line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendBoardToClient() throws IOException {
		OutputStream out = clientSocket.getOutputStream();
		PrintWriter writer = new PrintWriter(out);
		StringBuilder letters = new StringBuilder();
		for (String letter : boggleBoard) {
			letters.append(letter + " ");
		}
		writer.println(letters);
		writer.flush();

	}

	public void evaluate(String gameResults) throws IOException {
		String[] words = gameResults.split(" ");
		evaluator.receiveList(this, words);
	}

	public void sendResultsToClient(String status, int playerPoints) throws IOException {
		OutputStream out = clientSocket.getOutputStream();
		PrintWriter writer = new PrintWriter(out);

		writer.println(status + " " + String.valueOf(playerPoints));
		writer.flush();
		out.close();
	}

}
