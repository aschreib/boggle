package boggle;

import java.util.Random;

public class BoggleDice {

	/*
	 * hard code the sixteen dice, each with the specific six letters provide a
	 * random generator to choose one letter from each die for a list of the 16
	 * dice: www.everything2.com/title/Boggle
	 */

	private String[][] letterCubes;

	public BoggleDice() {
		letterCubes = new String[][] { { "A", "E", "A", "N", "E", "G" }, { "W", "N", "G", "E", "E", "H" },
				{ "A", "H", "S", "P", "C", "O" }, { "L", "N", "H", "N", "R", "Z" }, { "A", "S", "P", "F", "F", "K" },
				{ "T", "S", "T", "I", "Y", "D" }, { "O", "B", "J", "O", "A", "B" }, { "O", "W", "T", "O", "A", "T" },
				{ "I", "O", "T", "M", "U", "C" }, { "E", "R", "T", "T", "Y", "L" }, { "R", "Y", "V", "D", "E", "L" },
				{ "T", "O", "E", "S", "S", "I" }, { "L", "R", "E", "I", "X", "D" }, { "T", "E", "R", "W", "H", "V" },
				{ "E", "I", "U", "N", "E", "S" }, { "N", "U", "I", "H", "M", "Qu" } };
		// instantiate 2 dimensional array and hard-code letters in

	}

	public String[] getLetters() {
		String[] letters = new String[16];
		int counter = 0;
		Random randomGen = new Random();
		int randomNum;
		for (int i = 0; i < letterCubes.length; i++) {
			randomNum = randomGen.nextInt(6);
			letters[counter++] = letterCubes[i][randomNum];
		}
		// uses random gen in a double for-loop to select a letter from each die
		// and add it to the letters array
		return letters;
	}
}
