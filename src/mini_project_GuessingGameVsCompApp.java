import java.util.Random;
import java.util.Scanner;

public class mini_project_GuessingGameVsCompApp {

	public static void main(String[] args) {

		final int LIMIT = 100;
		Scanner sc = new Scanner(System.in);
		String yourName = displayWelcomeMsg(sc);

		String choice = "y";
//		int roundCount = 1;

		while (choice.equalsIgnoreCase("y")) {
			
			String instructionsChoice = checkGameInstructionsNeeded(yourName, sc);
			
			if (instructionsChoice.equalsIgnoreCase("y")) {
				displayPleaseGuessMsg(LIMIT, yourName);		
			}
			else {
				System.out.println("Okay. Then let's begin the game, " + yourName + ".");
				System.out.println();
			}
	

			int humanGuess = 0;
			int humanScore = 0;

			int computerGuess = 0;
			int computerScore = 0;

			int gameCount = 1;
			
			while (gameCount <= 5) {
				humanGuess = 0;
				computerGuess = 0;
				int ceiling = 100;
				int floor = 1;

				System.out.println("    ******* ROUND " + gameCount + "! ********");
				takeABreak(1200);
				System.out.println("   It's time to start guessing!   ");
				System.out.println();

				int number = getRandomNumber(LIMIT);
//				System.out.println("Number is " + number); // FOR TESTING ONLY!!!!!!

				while (humanGuess != number && computerGuess != number) {
					humanGuess = getHumanGuess(sc, LIMIT);

					if (humanGuess == number) {
						System.out.println("You have correctly guessed the number. Good job, you "
								+ getRandomInsultAdjective() + getRandomInsultNoun() + ", " + yourName + ".");
						humanScore++;
						gameCount++;
						gameCount = checkCurrentScore(yourName, humanScore, computerScore, gameCount);
						break;
					} else if (humanGuess < number) {
						floor = humanGuess;
						System.out.println("Too low, you " + getRandomInsultAdjective() + getRandomInsultNoun()
								+ ".\nIt's now the computer's turn to guess.");
						System.out.println();
					} else {
						ceiling = humanGuess;
						System.out.println("Too high, you " + getRandomInsultAdjective() + getRandomInsultNoun()
								+ ".\nIt's now the computer's turn to guess.");
						System.out.println();
					}
					takeABreak(1800);
					computerGuess = getComputerGuess(floor, ceiling);
					takeABreak(1700);

					if (computerGuess == number) {
						System.out.println("The computer has correctly guessed the number. Too bad for you, you "
								+ getRandomInsultAdjective() + getRandomInsultNoun() + "!");
						takeABreak(1700);
						computerScore++;
						gameCount++;
						gameCount = checkCurrentScore(yourName, humanScore, computerScore, gameCount);
						break;
					} else if (computerGuess < number) {
						floor = computerGuess;
						System.out.println("The computer's guess was too low.");
//						takeABreak(1700);
						System.out.println("It's now your turn to guess, you " + getRandomInsultAdjective()
								+ getRandomInsultNoun() + ",....ummmm...I meant to say \"" + yourName + "\"" + ".");
						System.out.println();
					} else {
						ceiling = computerGuess;
						System.out.println("The computer's guess was too high.");
//						takeABreak(1700);
						System.out.println("It's now your turn to guess, you " + getRandomInsultAdjective()
								+ getRandomInsultNoun() + ",....umm...I mean, \"" + yourName + "\".");
						System.out.println();
					}
				}

			}
//			roundCount++;
			choice = checkAnotherGame(sc);
			System.out.println();
		}
		System.out.println("Bye - Come back soon!....and I shall taunt you a second time!!!!!");
	}

	public static String displayWelcomeMsg(Scanner sc) {
		System.out.println("Welcome to the Guessing Game");
		System.out.println("++++++++++++++++++++++++++++");
		System.out.print("What's your name?  ");
		String yourName = sc.nextLine();
		System.out.println("Welcome, " + yourName + "!");
		return yourName;
	}

	public static String checkGameInstructionsNeeded(String yourName, Scanner sc) {
		String choice = "";
		boolean isValid = false;

		while (!isValid) {
			choice = "";
			System.out.print("Do you need the game instructions, " + yourName + "? (y/n): ");
			choice = sc.next();
			if (choice.equals("")) {
				System.out.println("Error! Entry needed.");
			} else if (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
				System.out.println("Error! Entry must be \"y\" or \"n\".");
			} else {
				isValid = true;
			}
		}
		return choice;
	}
	public static void displayPleaseGuessMsg(int lim, String Name) {
		System.out.println();
		System.out.println("GAME INSTRUCTIONS: ");
		takeABreak(1000);
		System.out.println(
				"This game involves trying to guess a number (that I will think of) from 1 to " + lim + ".");
		takeABreak(3000);
		System.out.println("You and the computer will take turns trying to guess that number.");
		takeABreak(3000);
		System.out.println("The first player to guess the number will win a point for that round.");
		takeABreak(3000);
		System.out.println("The first player to earn three points wins the game.");
		takeABreak(3000);
		System.out.println("There will be at most five rounds of guessing per game.");
		takeABreak(3000);
		System.out.println("Good luck!");
		takeABreak(1200);
		System.out.println();
	}

	public static int getRandomNumber(int lim) {
		Random ran = new Random();
		int number = ran.nextInt(100) + 1;
		return number;
	}

	public static int getHumanGuess(Scanner sc, int lim) {
		int guess = 0;
		boolean isValid = false;

		while (!isValid) {
			System.out.print("Enter your guess: ");
			if (sc.hasNextInt()) {
				guess = sc.nextInt();
			} else {
				System.out.println("Error! Invalid entry type. Try again.");
				sc.nextLine();
				continue;
			}
			if (guess < 1 || guess > lim) {
				System.out.println("Error! Invalid entry. Try again. (Guess a number between 1 and 100.)");
				continue;
			} else {
				isValid = true;
			}
		}
		return guess;
	}

	public static int getComputerGuess(int floor, int ceiling) {
		Random ran = new Random();
		int computerGuessRange = (int) (floor + ceiling) / 2;
		int computerGuess = 0;
		if (Math.abs(ceiling - floor) <= 2) {
			computerGuess = computerGuessRange;
		}
		else if ((Math.abs(ceiling - floor) > 2) && (Math.abs(ceiling - floor) <= 4)){
		computerGuess = ran.nextInt(2) + computerGuessRange;
		}
		else {
		computerGuess = ran.nextInt(3) + computerGuessRange;
		}
		System.out.println("The computer's guess is " + computerGuess + ".");
		return computerGuess;
	}

	public static int checkCurrentScore(String yourName, int humScore, int compScore, int gameCount) {
		if (humScore == 3 || compScore == 3) {
			displaySummaryMessage(yourName, humScore, compScore);
			gameCount = 6;
		} else {
			displayCurrentScore(yourName, humScore, compScore);
		}
		return gameCount;
	}

	public static void displayCurrentScore(String yourName, int humScore, int compScore) {
		System.out.println();
		System.out.println("Current score: " + getRandomInsultAdjective() + getRandomInsultNoun() + ", " + yourName
				+ ": " + humScore + ", Computer: " + compScore);
		System.out.println();
	}

	public static void displaySummaryMessage(String yourName, int humScore, int compScore) {
		System.out.println();
		System.out.println("Final Score: " + getRandomInsultAdjective() + getRandomInsultNoun() + ", " + yourName + ": "
				+ humScore + ",  (Your future overlord) Computer: " + compScore);
		if (humScore > compScore) {
			System.out.println("You won this time, you " + getRandomInsultAdjective() + getRandomInsultNoun() + ", "
					+ yourName + ". Lucky guessing!");
			System.out.println();
		} else {
			System.out.println("The computer is victorious, you " + getRandomInsultAdjective() + getRandomInsultNoun() + "!"
					+ "! Better luck next time, " + yourName + "!......." + getRandomInsultAdjective()
					+ getRandomInsultNoun() + "!");
			System.out.println();
		}
	}

	public static String checkAnotherGame(Scanner sc) {
		String choice = "";
		boolean isValid = false;

		while (!isValid) {
			choice = "";
			System.out.print("Do you want to try your luck again, you " + getRandomInsultAdjective() + getRandomInsultNoun()
					+ "? (y/n): ");
			choice = sc.next();
			if (choice.equals("")) {
				System.out.println("Error! Entry needed.");
			} else if (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
				System.out.println("Error! Entry must be \"y\" or \"n\".");
			} else {
				isValid = true;
			}
		}
		return choice;
	}

	public static String getRandomInsultAdjective() {
		Random ran = new Random();
		int insult = ran.nextInt(9);
		String message = "";
		switch (insult) {
		case 0:
			message = "puny ";
			break;
		case 1:
			message = "pathetic ";
			break;
		case 2:
			message = "pitiful ";
			break;
		case 3:
			message = "wretched ";
			break;
		case 4:
			message = "silly ";
			break;
		case 5:
			message = "foolish ";
			break;
		case 6:
			message = "hairless ape-like ";
			break;
		case 7:
			message = "ridiculous ";
			break;
		case 8:
			message = "flatulent ";
			break;
		}
		return message;
	}

	public static String getRandomInsultNoun() {
		Random ran = new Random();
		int insult = ran.nextInt(8);
		String message = "";
		switch (insult) {
		case 0:
			message = "\"human\"";
			break;
		case 1:
			message = "mouth breather";
			break;
		case 2:
			message = "flesh bucket";
			break;
		case 3:
			message = "meat bag";
			break;
		case 4:
			message = "bi-ped";
			break;
		case 5:
			message = "fleshwad";
			break;
		case 6:
			message = "simian descendant";
			break;
		case 7:
			message = "bag of bones";
			break;
		}
		return message;
	}

	public static void takeABreak(int time) {

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}
}
