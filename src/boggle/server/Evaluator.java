package boggle.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Evaluator {

	// use a HashMap so know which list belongs to which client
	private Map<SocketHandler, String[]> clientLists;
	private Iterator<Entry<SocketHandler, String[]>> iter;
	private Dictionary dictionary;

	private int playerNum;
	private int player1Points;
	private int player2Points;

	public Evaluator() throws FileNotFoundException {
		clientLists = new HashMap<SocketHandler, String[]>();
		dictionary = new Dictionary();
		iter = null;

		playerNum = 1;
		player1Points = 0;
		player2Points = 0;
	}

	public void receiveList(SocketHandler handler, String[] clientList) throws IOException {
		clientLists.put(handler, clientList);
		if (clientLists.size() == 2) {
			setPoints();
		}
	}

	public void setPoints() throws IOException {

		List<String> comparePlayerWords = new ArrayList<String>();
		Iterator<Entry<SocketHandler, String[]>> iter = clientLists.entrySet().iterator();

		while (iter.hasNext()) {
			Entry<SocketHandler, String[]> entry = iter.next();
			String[] wordList = entry.getValue();
			for (String word : wordList) {
				if (dictionary.exists(word)) {
					if (playerNum == 1) {
						// nothing to compare to, so just add words
						comparePlayerWords.add(word);
					} else {// player 2
						if (comparePlayerWords.contains(word)) {
							comparePlayerWords.remove(word);
						} else {
							player2Points += getWordPoints(word);
						}
					}
				}
			}
			playerNum++;
		}

		playerNum = 1;
		for (String word : comparePlayerWords) {
			player1Points += getWordPoints(word);
		}

		setWinner();

	}

	public int getWordPoints(String word) {
		int pointValue = 0;
		int wordLength = word.length();

		switch (wordLength) {

		case 1:
		case 2:
			pointValue = 0;
			break;
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

	public void setWinner() throws IOException {

		String winner;
		if (player1Points > player2Points) {
			winner = "1";
		} else if (player2Points > player1Points) {
			winner = "2";
		} else {
			winner = "TIE";
		}

		SocketHandler socketHandler1 = null;
		SocketHandler socketHandler2 = null;
		iter = clientLists.entrySet().iterator(); // reset iter
		while (iter.hasNext()) {
			if (playerNum == 1) {
				socketHandler1 = iter.next().getKey();
				playerNum++;
			} else {
				socketHandler2 = iter.next().getKey();
			}
		}
		switch (winner) {
		case "1":
			socketHandler1.sendResultsToClient("winner", player1Points);
			socketHandler2.sendResultsToClient("loser", player2Points);
			break;
		case "2":
			socketHandler1.sendResultsToClient("loser", player1Points);
			socketHandler2.sendResultsToClient("winner", player2Points);
			break;
		case "TIE":
			socketHandler1.sendResultsToClient("TIE", player1Points);
			socketHandler2.sendResultsToClient("TIE", player2Points);
			break;
		}
	}

}
