package com.hangman;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class DisplayConsole {
	private static final Logger LOGGER = Logger.getLogger(DisplayConsole.class);

	public void display(char[] guessedChars, String pattern, int chancesLeft) {
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

	private void printWinningMessage(String answer) {
		printStarBorder();
		System.out.println("HURRAY!!! YOU WON THE GAME :)");
		System.out.println("The word is : " + answer);
		printStarBorder();
	}

	private void printLossingMessage(String answer) {
		printStarBorder();
		System.out.println("SORRY YOU LOST THE GAME :(");
		System.out.println("The word is : " + answer);
		printStarBorder();
	}

	private void printGameInformation(String pattern, int remainingChances, char[] guessedChars) {
		System.out.print("Chances remaining: " + remainingChances + ", ");
		System.out.println("Characters used: " + new String(guessedChars));
		System.out.println();
		System.out.println("Guess the word:  " + pattern);
		System.out.println();
		System.out.print("Enter a character: ");
	}
}
