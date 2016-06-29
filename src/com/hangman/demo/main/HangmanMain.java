package com.hangman.demo.main;

import java.io.IOException;
import java.util.Scanner;

import com.hangman.demo.config.HangmanConfiguration;

public class HangmanMain {

	public static void main(String[] args) throws IOException{
		
		String selectedWord = HangmanConfiguration.selectWordFromList();
		System.out.println(selectedWord);
		int charactersToHide = HangmanConfiguration.getCharactersToHide();
		System.out.println(charactersToHide);
		String updatedWord = HangmanConfiguration.hideCharacters();
		System.out.println(updatedWord);
		
		
		Scanner scanner = new Scanner(System.in);
		while (updatedWord.contains("_") && HangmanConfiguration.getChances() != 0) {
			System.out.println("================================================");
			System.out.println("Chances remaining: " + HangmanConfiguration.getChances());
			System.out.println("Word length: " + HangmanConfiguration.getSelectedWord().length());
			System.out.println("Characters used: " + HangmanConfiguration.getCharacterUsed().toString());
			System.out.println("================================================");
			System.out.print("Enter a character: ");
			char userEnteredCharacter = scanner.next().trim().charAt(0);
			System.out.println(userEnteredCharacter);
			
			boolean characterExist = HangmanConfiguration.checkCharacterValid(userEnteredCharacter);
			System.out.println(characterExist);
			
			if (characterExist) {
				updatedWord = HangmanConfiguration.updateWord(userEnteredCharacter);
				System.out.println(updatedWord);
				
			}
			
			
		}
		scanner.close();
	}
}
