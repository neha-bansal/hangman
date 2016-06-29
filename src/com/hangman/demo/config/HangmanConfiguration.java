package com.hangman.demo.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HangmanConfiguration {
	
	private static List<String> wordsList = new ArrayList<>();
	private static String selectedWord;
	private static String updatedWord;
	private static int chances = 10;
	private static int wordLength;
	private static StringBuilder characterUsed = new StringBuilder();
	private static int charactersToHide;
	
	static {
		wordsList.add("january");
		wordsList.add("adventure");
		wordsList.add("holiday");
		wordsList.add("technical");
		wordsList.add("wednesday");
		wordsList.add("interaction");
		wordsList.add("ireland");
		wordsList.add("hangman");
		wordsList.add("gliders");
		wordsList.add("scientific");
	}
	
	public static String selectWordFromList() {
		
		selectedWord = wordsList.get((int) (Math.random() * 10));
		return selectedWord;
	}

	public static int getCharactersToHide() {
		wordLength = selectedWord.length();
		charactersToHide = (int) (Math.random() * ((wordLength - 2) + 1) + 2);
		return charactersToHide;
	}
	
	public static String hideCharacters() {
		Random random = new Random();
		Set<Integer> charactersToHideSet = new HashSet<>();
		
		for (int i=1; i < charactersToHide; i++) {
			charactersToHideSet.add(random.nextInt(selectedWord.length())); 
		}
		Iterator<Integer> iterator = charactersToHideSet.iterator();
		StringBuilder updatedWordBuilder = new StringBuilder(selectedWord);
		
		while(iterator.hasNext()) {
			int characterPlace = iterator.next();
			updatedWordBuilder.setCharAt(characterPlace, '_');
		}
		updatedWord = updatedWordBuilder.toString();
		return updatedWord.toString();
	}
	
	public static boolean checkCharacterValid(char enteredCharacter) {
		int indexInSelectedWord = selectedWord.indexOf(enteredCharacter);
		characterUsed.append(enteredCharacter);
		
		while (indexInSelectedWord != -1) {
			if (updatedWord.indexOf(enteredCharacter) == -1) {
				return true;
			}
			indexInSelectedWord = selectedWord.indexOf(enteredCharacter, indexInSelectedWord + 1);
		}
		chances--;
		return false;
	}
	
	public static String updateWord(char enteredCharacter) {
		StringBuilder updatedWordBuilder = new StringBuilder(updatedWord);
		int enteredCharacterIndex = selectedWord.indexOf(enteredCharacter);
		while (enteredCharacterIndex != -1) {
			updatedWordBuilder.setCharAt(enteredCharacterIndex, enteredCharacter);
			enteredCharacterIndex = selectedWord.indexOf(enteredCharacter, enteredCharacterIndex + 1);
		}
		
		updatedWord = updatedWordBuilder.toString();
		return updatedWord;
	}
	
	public static int getChances() {
		return chances;
	}
	
	public static String getSelectedWord() {
		return selectedWord;
	}

	public static void setSelectedWord(String selectedWord) {
		HangmanConfiguration.selectedWord = selectedWord;
	}

	public static StringBuilder getCharacterUsed() {
		return characterUsed;
	}

	public static void setCharacterUsed(StringBuilder characterUsed) {
		HangmanConfiguration.characterUsed = characterUsed;
	}

}
