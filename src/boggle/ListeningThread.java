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

		try {
			InputStream input = socket.getInputStream();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				if (letters[0] == null) {
					String[] letterList = line.split(" ");
					int letterPos = 0;
					for (int letterNum = 0; letterNum < 16; letterNum++) {
						letters[letterPos++] = letterList[letterNum];
					}

					client.getGui().createBoard(letters);
					Timer timer = new Timer(client);
					timer.startTimer();
				} else {
					System.out.println("waiting for results");
					String[] results = reader.readLine().split(" ");
					System.out.println("read results");
					client.getGui().showResults(results);
				}
			}

		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}