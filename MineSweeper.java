package minesweeper;

import java.util.Random;

class MineSweeper {

	// public static char[][] field = new char[][]{{'.','.','.','*','.'},
	// {'.','.','*','.','.'},
	// {'*','.','*','*','.'},
	// {'.','*','.','*','.'},
	// {'.','*','*','.'}};

	public static final int[][] DIRECTIONS = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
			{ 1, -1 }, { 1, 0 }, { 1, 1 } };

	public static int getNeighbours(int x, int y, char[][] field) {
		int count = 0;
		for (int i = 0; i < DIRECTIONS.length; i++) {
			int di = DIRECTIONS[i][0] + x;
			int dj = DIRECTIONS[i][1] + y;
			if (di >= 0 && dj >= 0 && di < field.length && dj < field[di].length) {
				if (field[di][dj] == '*') {
					count++;
				}
			}
		}
		return count;
	}

	public static char[][] generateMines(char[][] gameField) {
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j < gameField[i].length; j++) {
				double rand = Math.random();
				if (rand > 0.5) {
					gameField[i][j] = '*';
				} else {
					gameField[i][j] = '.';
				}
			}
		}
		return gameField;
	}

	public static char[][] replaceWithNumbers(char[][] gameFieldWithMines) {
		for (int i = 0; i < gameFieldWithMines.length; i++) {
			for (int j = 0; j < gameFieldWithMines[i].length; j++) {
				int neighbours = getNeighbours(i, j, gameFieldWithMines);
				if (neighbours > 0) {
					if (gameFieldWithMines[i][j] != '*') {
						gameFieldWithMines[i][j] = (char) (neighbours + 48);
					}
				}
			}
		}
		return gameFieldWithMines;
	}

	public static char[][] randomRowNumber() {
		Random random = new Random();
		char[][] field = new char[8][];
		for (int i = 0; i < field.length; i++) {
			field[i] = new char[random.nextInt(15) + 1];
		}
		return field;
	}

	public static void main(String args[]) {
		char[][] field = randomRowNumber();
		char[][] generatedField = generateMines(field);
		char[][] filledField = replaceWithNumbers(generatedField);
		for (char[] row : filledField) {
			System.out.println();
			for (char column : row) {
				System.out.print(column);
			}
		}
		// System.out.println(getNeighbours(3, 2, generatedField));
		// System.out.println(getNeighbours(3,2));
	}
}
