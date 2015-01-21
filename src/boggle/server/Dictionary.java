package boggle.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {

	private Set<String> wordList;

	public Dictionary() throws FileNotFoundException {
		wordList = new HashSet<String>();
		Scanner inputFile = new Scanner(new File("./boggleDictionary.txt"));

		while (inputFile.hasNext()) {
			wordList.add(inputFile.nextLine());
		}

		inputFile.close();
	}

	public boolean exists(String word) {
		return wordList.contains(word.toLowerCase());
	}

}
