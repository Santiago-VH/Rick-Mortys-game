package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.BoardManager;
import model.Box;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BoardManager manager = new BoardManager();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Welcome to Rick & Morty's adventure! Please digit the number of columns\n");
		int columns = Integer.parseInt(br.readLine());

		while (columns < 0) {
			System.out.println("Don't digit negative numbers, try again!\n");
			columns = Integer.parseInt(br.readLine());
		}

		System.out.println("Now digit the number of rows\n");
		int rows = Integer.parseInt(br.readLine());

		while (rows < 0) {
			System.out.println("Don't digit negative numbers, try again!\n");
			rows = Integer.parseInt(br.readLine());
		}

		int size = columns * rows;

		System.out.println("Please digit the amount of seeds you want on the game\n");

		int seedsAmount = Integer.parseInt(br.readLine());

		while (seedsAmount < 0 || seedsAmount > size || seedsAmount == 0) {
			System.out.println("An error ocurred while registering the number, try again!\n");
			seedsAmount = Integer.parseInt(br.readLine());
		}

		System.out.println("Please digit the amount of portals you want in the game (It can't be higher than half of "
				+ "the board's complete size\n)");

		int portalsAmount = Integer.parseInt(br.readLine());
		while (portalsAmount > 0.5 * size || portalsAmount < 0) {
			System.out.println("An error ocurred while registering the number, try again!\n");
			portalsAmount = Integer.parseInt(br.readLine());
		}

		System.out.println("Please type the username for player 1 (Rick)\n");
		String rick = br.readLine();

		System.out.println("Please type the username for player 2 (Morty)\n");
		String morty = br.readLine();

		manager.organizeGame(size, seedsAmount, portalsAmount, rick, morty);
		System.out.println("Game starts!\n");

		boolean isMortyMoving = false;
		int option;
		int mortySeedsCounter = 0;
		int rickSeedsCounter = 0;

		do {
			String name;
			if (isMortyMoving == false) {
				name = "Rick";
			} else {
				name = "Morty";
			}
			System.out.println("It's " + name + "'s turn! (player " + rick + "), what do you wanna do?\n"
					+ "1. Throw dice\n" + "2. View board\n" + "3. Show portals\n" + "4. Scoreboard");

			option = Integer.parseInt(br.readLine());
			while (option != 1 && option != 2 && option != 3 && option != 4) {
				System.out.println("Please select one of the 4 options, try again!");
				option = Integer.parseInt(br.readLine());
			}
			
			do {
				switchMenu(option, mortySeedsCounter, rickSeedsCounter, rows, columns, manager);
				option = Integer.parseInt(br.readLine());
			} while (option != 1);

			int diceValue = throwDice();

			System.out.println("You've rolled a " + diceValue + "! Do you wanna advance or go back?\n" + "1. Advance\n"
					+ "2. Go back");
			option = Integer.parseInt(br.readLine());
			while (option != 1 && option != 2) {
				System.out.println("Please select one of the 2 options, try again!");
				option = Integer.parseInt(br.readLine());
			}

			Box position = null;
			switch (option) {
			case 1:
				position = turnAdvance(diceValue, manager.getFirstLink().searchRick(size), isMortyMoving);

				break;
			case 2:
				position = turnBack(diceValue, manager.getFirstLink().searchRick(size), isMortyMoving);
				break;
			}

			int[] counters = checkPortals(position, mortySeedsCounter, rickSeedsCounter);
			mortySeedsCounter = counters[0];
			rickSeedsCounter = counters[1];

		} while (seedsAmount != (mortySeedsCounter + rickSeedsCounter));

	}

	public static Box turnAdvance(int diceValue, Box current, boolean mortyIsMoving) {
		if (diceValue == 0 && mortyIsMoving) {
			current.setMorty(true);
		} else if (diceValue == 0 && !mortyIsMoving) {
			current.setRick(true);

		} else {
			return turnAdvance(diceValue - 1, current.getNext(), mortyIsMoving);

		}
		return current;

	}

	public static Box turnBack(int diceValue, Box current, boolean mortyIsMoving) {
		if (diceValue == 0 && mortyIsMoving) {
			current.setMorty(true);
		} else if (diceValue == 0 && !mortyIsMoving) {
			current.setRick(true);

		} else {
			return turnBack(diceValue - 1, current.getPrevious(), mortyIsMoving);
		}
		return current;
	}

	public static int[] checkPortals(Box position, int mortySeedsCounter, int rickSeedsCounter) {

		int[] counters = { mortySeedsCounter, rickSeedsCounter };
		counters = checkSeeds(position, counters);
		if (position.hasPortal()) {
			if (position.isMorty()) {
				position.setMorty(false);
				position = position.getPortal();
				position.setMorty(true);
			} else if (position.isRick()) {
				position.setRick(false);
				position = position.getPortal();
				position.setRick(true);
			}
			counters = checkSeeds(position, counters);
		}
		return counters;
	}

	public static int[] checkSeeds(Box position, int[] counters) {
		if (position.isSeed()) {
			if (position.isMorty()) {
				position.setSeed(false);
				counters[0]++;
			} else if (position.isRick()) {
				position.setSeed(false);
				counters[1]++;
			}
		}
		return counters;
	}

	public static void switchMenu(int option, int mortySeedsCounter, int rickSeedsCounter, int columns, int rows,
			BoardManager manager) {

		
		switch (option) {

		case 1:
			throwDice();
			break;

		case 2:
			System.out.println(seeStandardBoard(columns, rows, manager));
			break;

		case 3:
			System.out.println(seePortals(columns, rows, manager));
			break;

		case 4:
			System.out.println(seeLeaderboard(mortySeedsCounter, rickSeedsCounter));
			break;
		}
	}
	
	public static String seePortals(int columns, int rows, BoardManager manager) {
		String boardView = "";
		boolean isPar = false;
		Box current = manager.getFirstLink();
		for (int i = 0; i < rows * columns; i++) {

			if (current.getID() % columns == 0 && !isPar) {
				if (current.hasPortal()) {
					boardView += "["+current.getPortal().getID()+"]  " + "\n";
				} else if (current.isRick() && current.isMorty()) {
					boardView += "[R&M]  " + "\n";
				} else if (current.isMorty()) {
					boardView += "[M]  " + "\n";
				} else if (current.isRick()) {
					boardView += "[R]  " + "\n";
				} else {
					boardView += "["+current.getID() + "] \n";
				}
				isPar = true;

			} else if (current.getID() % columns != 0 && !isPar) {
				if (current.isSeed()) {
					boardView += "["+current.getPortal().getID()+"]  ";
				} else if (current.isRick() && current.isMorty()) {
					boardView += "[R&M]  ";
				} else if (current.isMorty()) {
					boardView += "[M]  ";
				} else if (current.isRick()) {
					boardView += "[R] ";
				} else {
					boardView += "["+current.getID()+"]  ";
				}

			} else if (current.getID() % columns == 0 && isPar) {

				Box temp = current;
				do {

					if (temp.isSeed()) {
						boardView += "["+current.getPortal().getID()+"]  ";
					} else if (temp.isRick() && temp.isMorty()) {
						boardView += "[R&M]  ";
					} else if (temp.isMorty()) {
						boardView += "[M]  ";
					} else if (temp.isRick()) {
						boardView += "[R]  ";
					} else {
						boardView += "["+temp.getID()+"]  ";
					}
					temp = temp.getPrevious();

				} while (temp.getID() % columns != 0);
				boardView += "\n";
				isPar = false;
			}
			current = current.getNext();

		}
		return boardView;
	}
	

	public static String seeStandardBoard(int columns, int rows, BoardManager manager) {
		String boardView = "";
		boolean isPar = false;
		Box current = manager.getFirstLink();
		for (int i = 0; i < rows * columns; i++) {

			if (current.getID() % columns == 0 && !isPar) {
				if (current.isSeed()) {
					boardView += "[*]  " + "\n";
				} else if (current.isRick() && current.isMorty()) {
					boardView += "[R&M]  " + "\n";
				} else if (current.isMorty()) {
					boardView += "[M]  " + "\n";
				} else if (current.isRick()) {
					boardView += "[R]  " + "\n";
				} else {
					boardView += "["+current.getID() + "] \n";
				}
				isPar = true;

			} else if (current.getID() % columns != 0 && !isPar) {
				if (current.isSeed()) {
					boardView += "[*]  ";
				} else if (current.isRick() && current.isMorty()) {
					boardView += "[R&M]  ";
				} else if (current.isMorty()) {
					boardView += "[M]  ";
				} else if (current.isRick()) {
					boardView += "[R] ";
				} else {
					boardView += "["+current.getID()+"]  ";
				}

			} else if (current.getID() % columns == 0 && isPar) {

				Box temp = current;
				do {

					if (temp.isSeed()) {
						boardView += "[*]  ";
					} else if (temp.isRick() && temp.isMorty()) {
						boardView += "[R&M]  ";
					} else if (temp.isMorty()) {
						boardView += "[M]  ";
					} else if (temp.isRick()) {
						boardView += "[R]  ";
					} else {
						boardView += "["+temp.getID()+"]  ";
					}
					temp = temp.getPrevious();

				} while (temp.getID() % columns != 0);
				boardView += "\n";
				isPar = false;
			}
			current = current.getNext();

		}
		return boardView;
	}

	
	
	
	public static int throwDice() {
		int diceValue = (int) (Math.random() * (6) + 1);
		return diceValue;

	}

	public static String seeLeaderboard(int mortyCounter, int rickCounter) {
		String leaderboard = "Rick: " + rickCounter + " semillas\n" + "Morty: " + mortyCounter + " semillas";
		return leaderboard;

	}

}
