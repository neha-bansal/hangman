package com.hangman.demo.main;

import java.io.IOException;
import java.util.Scanner;

import com.hangman.demo.utility.HangmanUtility;

public class HangmanMain {

	public static void main(String[] args) throws IOException{
		
		String selectedWord = HangmanUtility.selectWordFromList();
		System.out.println(selectedWord);
		int charactersToHide = HangmanUtility.getCharactersToHide();
		System.out.println(charactersToHide);
		String updatedWord = HangmanUtility.hideCharacters();
		System.out.println(updatedWord);
		
		
		Scanner scanner = new Scanner(System.in);
		while (updatedWord.contains("_") && HangmanUtility.getRemainingChances() != 0) {
			System.out.println("================================================");
			System.out.println("Chances remaining: " + HangmanUtility.getRemainingChances());
			System.out.println("Word length: " + HangmanUtility.getSelectedWordLength());
			System.out.println("Characters used: " + HangmanUtility.getCharactersUsed());
			System.out.println("================================================");
			System.out.print("Enter a character: ");
			char userEnteredCharacter = scanner.next().trim().charAt(0);
			System.out.println(userEnteredCharacter);
			
			boolean characterExist = HangmanUtility.checkCharacterValid(userEnteredCharacter);
			System.out.println(characterExist);
			
			if (characterExist) {
				updatedWord = HangmanUtility.updateWord(userEnteredCharacter);
			}
			System.out.println(updatedWord);
		}
		scanner.close();
	}
}
