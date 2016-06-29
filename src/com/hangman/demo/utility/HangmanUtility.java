package com.hangman.demo.utility;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.hangman.demo.config.HangmanConfiguration;
import com.hangman.demo.domain.GameAttributes;

public class HangmanUtility {
	
	private static HangmanConfiguration hangmanCongiguration;
	private static GameAttributes gameAttributes;

	static {
		hangmanCongiguration = new HangmanConfiguration();
		gameAttributes = new GameAttributes();
	}
	
public static String selectWordFromList() {
		
		String selectedWord = hangmanCongiguration.getWordsList().get((int) (Math.random() * 10));
		gameAttributes.setSelectedWord(selectedWord);
		gameAttributes.setSelectedWordLength(selectedWord.length());
		return selectedWord;
	}

	public static int getCharactersToHide() {
		int maxCharactersToHide = gameAttributes.getSelectedWordLength() - 1;
		int minCharactersToHide = 2;
		int charactersToHide = (int) (Math.random() * ((maxCharactersToHide - minCharactersToHide) + 1) + minCharactersToHide);
		gameAttributes.setCharactersToHide(charactersToHide);
		return charactersToHide;
	}
	
	public static String hideCharacters() {
		Random random = new Random();
		Set<Integer> charactersToHideSet = new HashSet<>();
		
		for (int i=1; i <= gameAttributes.getCharactersToHide(); i++) {
			int pos = random.nextInt(gameAttributes.getSelectedWordLength());
			System.out.print(" " + pos);
			charactersToHideSet.add(pos); 
		}
		Iterator<Integer> iterator = charactersToHideSet.iterator();
		StringBuilder updatedWordBuilder = new StringBuilder(gameAttributes.getSelectedWord());
		
		while(iterator.hasNext()) {
			int characterPlace = iterator.next();
			updatedWordBuilder.setCharAt(characterPlace, '_');
		}
		gameAttributes.setUpdatedWord(updatedWordBuilder.toString());
		return updatedWordBuilder.toString();
	}
	
	public static boolean checkCharacterValid(char enteredCharacter) {
		int indexInSelectedWord = gameAttributes.getSelectedWord().indexOf(enteredCharacter);
		gameAttributes.getCharacterUsed().append(enteredCharacter);
		
		while (indexInSelectedWord != -1) {
			if (gameAttributes.getUpdatedWord().indexOf(enteredCharacter) == -1) {
				return true;
			}
			indexInSelectedWord = gameAttributes.getSelectedWord().indexOf(enteredCharacter, indexInSelectedWord + 1);
		}
		gameAttributes.setChances(gameAttributes.getChances() - 1); 
		return false;
	}
	
	public static String updateWord(char enteredCharacter) {
		StringBuilder updatedWordBuilder = new StringBuilder(gameAttributes.getUpdatedWord());
		int enteredCharacterIndex = gameAttributes.getSelectedWord().indexOf(enteredCharacter);
		while (enteredCharacterIndex != -1) {
			updatedWordBuilder.setCharAt(enteredCharacterIndex, enteredCharacter);
			enteredCharacterIndex = gameAttributes.getSelectedWord().indexOf(enteredCharacter, enteredCharacterIndex + 1);
		}
		
		gameAttributes.setUpdatedWord(updatedWordBuilder.toString());
		return updatedWordBuilder.toString();
	}
	
	public static int getRemainingChances() {
		return gameAttributes.getChances();
	}
	
	public static int getSelectedWordLength() {
		return gameAttributes.getSelectedWordLength();
	}
	
	public static String getCharactersUsed() {
		return gameAttributes.getCharacterUsed().toString();
	}
}
