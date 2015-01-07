package boggle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListeningThread extends Thread {

	private Socket socket;
	private Client client;

	public ListeningThread(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;
	}

	@Override
	public void run() {
		String[] letters = new String[16];
		InputStream input;

		try {
			input = socket.getInputStream();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			int letterPos = 0;
			while ((line = reader.readLine()) != null) {
				letters[letterPos++] = line;
			}

			client.getGui().createBoard(letters);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}