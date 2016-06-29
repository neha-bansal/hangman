package com.hangman;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class DisplayConsole {
	private static final Logger LOGGER = Logger.getLogger(DisplayConsole.class);

	public void displayInfo(List<Character> guessedChars, String pattern, int chancesLeft) {
		printBorder();
		printGameInformation(pattern, chancesLeft, guessedChars);
	}

	public char scanCharacter() {
		System.out.print("Enter a character: ");
		Scanner scanner = new Scanner(System.in);
		char userEnteredCharacter = scanner.next().trim().charAt(0);
		System.out.println(userEnteredCharacter);
		scanner.close();
		return userEnteredCharacter;
	}

	private void printBorder() {
		System.out.println("======================================================================");
	}

	private void printStarBorder() {
		System.out.println();
		System.out.println("*********************************************************************");
		System.out.println();
	}

	public void displayWinningMessage(String answer) {
		printStarBorder();
		System.out.println("HURRAY!!! YOU WON THE GAME :)");
		System.out.println("The word is : " + answer);
		printStarBorder();
	}

	public void displayLossingMessage(String answer) {
		printStarBorder();
		System.out.println("SORRY YOU LOST THE GAME :(");
		System.out.println("The word is : " + answer);
		printStarBorder();
	}

	private void printGameInformation(String pattern, int remainingChances, List<Character> guessedChars) {
		System.out.print("Chances remaining: " + remainingChances + ", ");
		System.out.println("Characters used: " + guessedChars);
		System.out.println();
		System.out.println("Guess the word:  " + pattern);
		System.out.println();
		System.out.print("Enter a character: ");
	}
}
