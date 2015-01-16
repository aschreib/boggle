package boggle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Evaluator {
	
	//use a HashMap so know which list belongs to which client
	private Map<SocketHandler, String[]> clientLists;
	private Dictionary dictionary;
	
	
	public Evaluator() throws FileNotFoundException{
		clientLists = new HashMap<SocketHandler, String[]>();
		dictionary = new Dictionary();
	}
	
	public void receiveList(SocketHandler handler, String[] clientList){
		clientLists.put(handler,  clientList);
		if(clientLists.size() == 2){
			checkForWinner();
		}
	}

	public void checkForWinner(){

		int playerNum = 1;
		int player1Points = 0;
		int player2Points = 0;
		Map<String, Socket> playersValidWords = new HashMap<String, Socket>();
        System.out.println("Checking for winner");
		for (Socket c : sockets) {
			InputStream in = c.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String word;
			int points;
			while ((word = reader.readLine()) != null) {
				if (dictionary.exists(word)) {
					if (playerNum == 2) {
						if (playersValidWords.containsKey(word)) {
							playersValidWords.remove(word);
						} else {
							playersValidWords.put(word, c);
							points = getWordPoints(word);
							player2Points += points;
						}
					} else {
						playersValidWords.put(word, c);
					}
				}
			}
			points = getWordPoints(word);
			playerNum += points;
		}

		for (String word : playersValidWords.keySet()) {
			if ((playersValidWords.get(word)).equals(sockets.get(0))) {
				player1Points++;
			}
		}

		String winner;
		if (player1Points > player2Points) {
			winner = "Player1";
		} else {
			winner = "Player2";
		}
		System.out.println("1 " + player1Points);
		System.out.println("2 " + player2Points);
        System.out.println(winner);
		sendResultsToClients(winner, player1Points, player2Points);

	
	}
	
	public int getWordPoints(String word) {
		int pointValue = 0;
		int wordLength = word.length();
		switch (wordLength) {
		case 3:
			pointValue = 1;
			break;
		case 4:
			pointValue = 2;
			break;
		case 5:
			pointValue = 3;
			break;
		case 6:
			pointValue = 4;
			break;
		case 7:
			pointValue = 5;
			break;
		case 8:
			pointValue = 11;
			break;
		default:
			pointValue = wordLength * 2;
			break;

		}
		return pointValue;
	}

}
