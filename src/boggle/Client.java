package boggle;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

	// this class interacts with the Server - have to include this code in this
	// class

	// this structure is just a suggestion - split between Client and ClientGUI
	// - we can change it

	// from server
	private ArrayList<String> words; // chosen by player
	private Timer timer; // don't have to use this class but probably has
	// everything built in that we need, look at java
	// docs
	// maybe this belongs in ClientGUI
	public ClientGUI gui;
	private Socket socket;
	private OutputStream out;

	public Client() throws UnknownHostException, IOException {
		ClientGUI gui = new ClientGUI(this);
		gui.setVisible(true);
		this.gui = gui;
		socket = new Socket("127.0.0.1", 8080);
		words = new ArrayList<String>();

	}

	public ArrayList<String> getWords() {
		return words;
	}

	public Timer getTimer() {
		return timer;
	}

	public ClientGUI getGui() {
		return gui;
	}

	public Socket getSocket() {
		return socket;
	}

	public OutputStream getOut() {
		return out;
	}

	public void startGame(String start) throws IOException {
		if (out == null) {// outputstream is only set once in a game
			out = socket.getOutputStream();
		}
		out.write(start.getBytes());
		out.flush();
		ListeningThread thread = new ListeningThread(socket, this);
		thread.start();

	}

	/*
	 * public void createBoard() throws IOException { //get letters from server
	 * // after gets board from server, calls this method
	 * 
	 * gui.createBoard(board); }
	 */

	public void addWord(String word) {
		// adds word to list
		words.add(word);
	}

	public void sendWords() {
		ArrayList<String> wordList = gui.getInputPanel().getSubmitPanel().getWordList();
		// when time is up sends list to server
		try {
			if (out == null) {
				out = socket.getOutputStream();
			}
			PrintWriter writer = new PrintWriter(out);
			writer.println("game results");
			for (String word : wordList) {
				writer.print(word + "\n");
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client();
	}

}