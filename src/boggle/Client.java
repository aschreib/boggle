package boggle;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

	private ClientGUI gui;
	private Socket socket;
	private Timer timer;
	private ArrayList<String> playersWords;

	public Client() throws UnknownHostException, IOException {
		gui = new ClientGUI(this);
		gui.setVisible(true);
		socket = new Socket("127.0.0.1", 8080);
		playersWords = new ArrayList<String>();

	}

	public ArrayList<String> getWords() {
		return playersWords;
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

	public void startGame(String start) throws IOException {

		OutputStream out = socket.getOutputStream();
		out.write(start.getBytes());
		out.flush();
		ListeningThread thread = new ListeningThread(socket, this);
		thread.start();

	}

	public void addWord(String word) {
		playersWords.add(word);
	}

	public void sendWords() throws IOException {

		StringBuilder list = new StringBuilder();
		ArrayList<String> words = gui.getInputPanel().getSubmitPanel().getWordList();

		for (String word : words) {
			list.append(word + " ");
		}

		OutputStream out = socket.getOutputStream();
		out.write(list.toString().getBytes());
		socket.shutdownOutput();

	}

	public static void main(String[] args) {
		try {
			new Client();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}