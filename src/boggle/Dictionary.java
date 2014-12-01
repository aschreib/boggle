package boggle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {

	private final List<String> wordList;

	public Dictionary() throws FileNotFoundException {
		wordList = new ArrayList<String>();
		Scanner inputFile = new Scanner(new File("./boggleDictionary.txt"));

		while (inputFile.hasNext()) {

			wordList.add(inputFile.nextLine());
		}

		inputFile.close();
	}



	public boolean exists(String word) {

		return wordList.contains(word.toUpperCase());
	}

}
