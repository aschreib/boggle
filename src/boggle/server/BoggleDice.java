package boggle.server;

import java.util.Random;

public class BoggleDice {

	// dice: www.everything2.com/title/Boggle

	private String[][] letterCubes;

	public BoggleDice() {
		letterCubes = new String[][] { { "A", "E", "A", "N", "E", "G" }, { "W", "N", "G", "E", "E", "H" },
				{ "A", "H", "S", "P", "C", "O" }, { "L", "N", "H", "N", "R", "Z" }, { "A", "S", "P", "F", "F", "K" },
				{ "T", "S", "T", "I", "Y", "D" }, { "O", "B", "J", "O", "A", "B" }, { "O", "W", "T", "O", "A", "T" },
				{ "I", "O", "T", "M", "U", "C" }, { "E", "R", "T", "T", "Y", "L" }, { "R", "Y", "V", "D", "E", "L" },
				{ "T", "O", "E", "S", "S", "I" }, { "L", "R", "E", "I", "X", "D" }, { "T", "E", "R", "W", "H", "V" },
				{ "E", "I", "U", "N", "E", "S" }, { "N", "U", "I", "H", "M", "QU" } };

	}

	public String[] getLetters() {
		// randomly choose 1 out of the 6 letters in each of the 16 rows
		String[] letters = new String[16];
		int counter = 0;
		Random randomGen = new Random();
		int randomNum;
		for (int i = 0; i < letterCubes.length; i++) {
			randomNum = randomGen.nextInt(6);
			letters[counter++] = letterCubes[i][randomNum];
		}

		return letters;
	}
}
