package boggle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

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
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			if(letters[0]==null){
			int letterPos = 0;
			for(int letterNum = 0; letterNum < 16; letterNum++){
				if((line = reader.readLine())!=null){
					letters[letterPos++] = line;
				}
			}

			client.getGui().createBoard(letters);
			Timer timer = new Timer(client);
			timer.startTimer();
			}else{
				String[] results = reader.readLine().split(" ");
				client.getGui().showResults(results);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}