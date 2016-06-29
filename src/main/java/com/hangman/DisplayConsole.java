package com.hangman;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * Class to handle all the input/output functions.
 *
 */
public class DisplayConsole {
	private static final Logger LOGGER = Logger.getLogger(DisplayConsole.class);
	private Scanner scanner;

	/**
	 * initializes a new scanner to read the user input.
	 */
	public void init() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Closes the scanner after the game ends.
	 */
	public void close() {
		scanner.close();
	}

	/**
	 * Display the information about the current game state.
	 * 
	 * @param gameLevel
	 *            Game level
	 * @param guessedChars
	 *            characters already guessed by user
	 * @param pattern
	 *            word with hidden characters
	 * @param chancesLeft
	 *            the remaining chances
	 */
	public void displayGameInfo(GameLevel level, List<Character> guessedChars, String pattern, int chancesLeft) {
		System.out.println("======================================================================");
		printGameInformation(level, pattern, chancesLeft, guessedChars);
	}

	/**
	 * Reads a character entered by the user, from the console.
	 * 
	 * @return the character entered by user
	 */
	public char scanCharacter() {
		System.out.print("Enter a character: ");
		char userEnteredCharacter = scanner.next().charAt(0);
		LOGGER.debug("Character entered by the user: " + userEnteredCharacter);
		return userEnteredCharacter;
	}

	public void displayBanner() {
		System.out.println("*********************************************************************");
		System.out.println("*                                                                   *");
		System.out.println("*                       Welcome to Hangman                          *");
		System.out.println("*                                                                   *");
		System.out.println("*********************************************************************");

	}

	private void printStarBorder() {
		System.out.println("\n*********************************************************************\n");
	}

	/**
	 * Displays the winning message
	 * 
	 * @param answer
	 *            the guessed word
	 */
	public void displayWinningMessage(String answer) {
		printStarBorder();
		System.out.println("HURRAY!!! YOU WON THE GAME :)");
		System.out.println("The word is : " + answer);
		printStarBorder();
		LOGGER.debug("User have won the game for word: " + answer);
	}

	/**
	 * Displays the winning message
	 * 
	 * @param answer
	 *            the guessed word
	 */
	public void displayLosingMessage(String answer) {
		printStarBorder();
		System.out.println("SORRY YOU LOST THE GAME :(");
		System.out.println("The word is : " + answer);
		printStarBorder();
		LOGGER.debug("User have lost the game for word: " + answer);
	}

	private void printGameInformation(GameLevel level, String pattern, int remainingChances, List<Character> guessedChars) {
		System.out.print("Game level: " + level+", ");
		System.out.print("Chances remaining: " + remainingChances + ", ");
		System.out.println("Characters used: " + guessedChars + "\n");
		System.out.println("Guess the word:  " + pattern + "\n");
	}
}
