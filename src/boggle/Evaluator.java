package boggle;

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
	private Dictionary dictionary;

	public Evaluator() throws FileNotFoundException {
		clientLists = new HashMap<SocketHandler, String[]>();
		dictionary = new Dictionary();
	}

	public void receiveList(SocketHandler handler, String[] clientList) throws IOException {
		clientLists.put(handler, clientList);
		if (clientLists.size() == 2) {
			checkForWinner();
		}
	}

	public void checkForWinner() throws IOException {

		int playerNum = 1;
		int player1Points = 0;
		int player2Points = 0;
		List<String> comparePlayerWords = new ArrayList<String>();
		System.out.println("Checking for winner");
		Iterator<Entry<SocketHandler, String[]>> iter = clientLists.entrySet().iterator();

		while (iter.hasNext()) {
			Entry<SocketHandler, String[]> entry = iter.next();
			String[] wordList = entry.getValue();
			for (String word : wordList) {
				if (dictionary.exists(word)) {
					if (playerNum == 1) {
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

		String winner;
		if (player1Points > player2Points) {
			winner = "1";
		} else {
			winner = "2";
		}
		System.out.println("1 " + player1Points);
		System.out.println("2 " + player2Points);
		System.out.println(winner);

		SocketHandler socketHandler;
		while (iter.hasNext()) {
			socketHandler = iter.next().getKey();

			switch (winner) {
			case "1":
				if (playerNum == 1) {
					socketHandler.sendResultsToClient("winner", player1Points);
					playerNum++;
				} else {// player 2
					socketHandler.sendResultsToClient("loser", player2Points);
				}
				break;
			case "2":
				if (playerNum == 2) {
					socketHandler.sendResultsToClient("loser", player1Points);
					playerNum++;
				} else {// player 2
					socketHandler.sendResultsToClient("winner", player2Points);
				}
				break;
			}
		}

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
