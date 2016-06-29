package com.hangman.example.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.hangman.example.utility.HangmanUtility;

/**
 * Main class to play the game.
 */
public class HangmanMain {
	
	private static Logger logger = Logger.getLogger(HangmanMain.class);

	public static void main(String[] args) {
		
		logger.info("The Hangman Game Started...");
		
		String selectedWord = HangmanUtility.selectWordFromList();
		HangmanUtility.getCharactersToHideCount();
		String updatedWord = HangmanUtility.hideCharacters();
		
		
		Scanner scanner = new Scanner(System.in);
		while (updatedWord.contains("_") && HangmanUtility.getRemainingChances() != 0) {
			
			printBorder();
			printGameInformation(updatedWord);
			
			char userEnteredCharacter = scanner.next().trim().charAt(0);
			printBorder();
			
			boolean characterExist = HangmanUtility.checkCharacterValid(userEnteredCharacter);
			
			if (characterExist) {
				updatedWord = HangmanUtility.updateWord(userEnteredCharacter);
			}
		}
		scanner.close();
		
		if (HangmanUtility.getRemainingChances() == 0) {
			printStarBorder();
			System.out.println("SORRY YOU LOST THE GAME :(");
			System.out.println("The word is : " + selectedWord);
			printStarBorder();
		} else {
			printStarBorder();
			System.out.println("HURRAY!!! YOU WON THE GAME :)");
			System.out.println("The word is : " + updatedWord);
			printStarBorder();
		}
		logger.info("The Hangman Game ended...");
	}
	
	private static void printBorder() {
		System.out.println("======================================================================");
	}
	
	private static void printStarBorder() {
		System.out.println();
		System.out.println("*********************************************************************");
		System.out.println();
	}
	
	private static void printGameInformation(String updatedWord) {
		System.out.print("Chances remaining: " + HangmanUtility.getRemainingChances() + ", ");
		System.out.print("Word length: " + HangmanUtility.getSelectedWordLength() + ", ");
		System.out.println("Characters used: " + HangmanUtility.getCharactersUsed());
		System.out.println();
		System.out.println("Guess the word:  " + updatedWord);
		System.out.println();
		System.out.print("Enter a character: ");
	}
}
