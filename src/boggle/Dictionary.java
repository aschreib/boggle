package boggle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {

	private final Map<String, String> wordList;

	public Dictionary() throws FileNotFoundException {
		wordList = new HashMap<String, String>();
		Scanner inputFile = new Scanner(new File("./OWL.txt"));

		while (inputFile.hasNext()) {

			wordList.put(inputFile.next(), inputFile.nextLine());
		}

		inputFile.close();
	}

	public Iterator<String> iterator() {
		return wordList.keySet().iterator();
	}


	public boolean exists(String word) {

		return wordList.containsKey(word.toUpperCase());
	}

}
