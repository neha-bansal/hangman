package com.hangman.example.utility;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hangman.example.config.HangmanConfiguration;
import com.hangman.example.domain.GameAttributes;

/**
 * The utility class for the Hangman game.
 */
public class HangmanUtility {
	
	private static Logger logger = Logger.getLogger(HangmanUtility.class);
	private static HangmanConfiguration hangmanCongiguration;
	private static GameAttributes gameAttributes;

	static {
		hangmanCongiguration = new HangmanConfiguration();
		gameAttributes = new GameAttributes();
	}
	
	/**
	 * Selects a random word from the words list to be used for the game.
	 * Updates the gameAttributes accordingly.
	 * @return the selected word
	 */
	public static String selectWordFromList() {
		
		String selectedWord = hangmanCongiguration.getWordsList().get((int) (Math.random() * (hangmanCongiguration.getWordsList().size()) - 1));
		gameAttributes.setSelectedWord(selectedWord);
		gameAttributes.setSelectedWordLength(selectedWord.length());
		logger.info("Word selected from the list: " + selectedWord);
		return selectedWord;
	}

	/**
	 * Find out the number of characters to hide in the selected word. 
	 * Characters to hide lies between (2 to selectedWordLength - 1).
	 * @return number of characters to hide
	 */
	public static int getCharactersToHideCount() {
		int maxCharactersToHide = gameAttributes.getSelectedWordLength() - 1;
		int minCharactersToHide = 2;
		int charactersToHide = (int) (Math.random() * ((maxCharactersToHide - minCharactersToHide) + 1) + minCharactersToHide);
		gameAttributes.setCharactersToHideCount(charactersToHide);
		
		logger.info("Number of characters to hide from the selected word: " + charactersToHide);
		return charactersToHide;
	}
	
	/**
	 * It hides the characters in the selected word depending on the value of characterToHide.
	 * @return the updated word with hidden characters
	 */
	public static String hideCharacters() {
		Random random = new Random();
		Set<Integer> positionsToHideSet = new HashSet<>();
		
		for (int i=1; i <= gameAttributes.getCharactersToHideCount(); i++) {
			positionsToHideSet.add(random.nextInt(gameAttributes.getSelectedWordLength())); 
		}
		logger.info("Positions to hide in the selected word: " + positionsToHideSet);
		Iterator<Integer> iterator = positionsToHideSet.iterator();
		StringBuilder updatedWordBuilder = new StringBuilder(gameAttributes.getSelectedWord());
		
		while(iterator.hasNext()) {
			int characterPlace = iterator.next();
			updatedWordBuilder.setCharAt(characterPlace, '_');
		}
		gameAttributes.setUpdatedWord(updatedWordBuilder.toString());
		
		logger.info("Updated word after hiding the characters: " + updatedWordBuilder.toString());
		return updatedWordBuilder.toString();
	}
	
	/**
	 * It checks that the character entered by the user is a valid character or not, i.e., it will be used in the updated word.
	 * @param enteredCharacter the character entered by the user
	 * @return whether entered character is valid or not.
	 */
	public static boolean checkCharacterValid(char enteredCharacter) {
		int indexInSelectedWord = gameAttributes.getSelectedWord().indexOf(enteredCharacter);
		
		updateCharactersUsed(enteredCharacter);
		
		while (indexInSelectedWord != -1) {
			if (gameAttributes.getUpdatedWord().charAt(indexInSelectedWord) != enteredCharacter) {
				logger.info("The character entered by the user is valid");
				return true;
			}
			indexInSelectedWord = gameAttributes.getSelectedWord().indexOf(enteredCharacter, indexInSelectedWord + 1);
		}
		updateRemainingChances();
		
		logger.info("The character entered by the user: " + enteredCharacter + " is not valid");
		return false;
	}
	
	/**
	 * Updates the word if the user entered character exists in the selected word.
	 * @param enteredCharacter the character entered by the user
	 * @return the updated word with user entered character added
	 */
	public static String updateWord(char enteredCharacter) {
		StringBuilder updatedWordBuilder = new StringBuilder(gameAttributes.getUpdatedWord());
		int enteredCharacterIndex = gameAttributes.getSelectedWord().indexOf(enteredCharacter);
		
		while (enteredCharacterIndex != -1) {
			updatedWordBuilder.setCharAt(enteredCharacterIndex, enteredCharacter);
			enteredCharacterIndex = gameAttributes.getSelectedWord().indexOf(enteredCharacter, enteredCharacterIndex + 1);
		}
		gameAttributes.setUpdatedWord(updatedWordBuilder.toString());
		
		logger.info("The updated word, after user entered the valid character: " + updatedWordBuilder.toString());
		return updatedWordBuilder.toString();
	}
	
	/**
	 * Return the chances remaining for the user.
	 * @return the chances remaining
	 */
	public static int getRemainingChances() {
		return gameAttributes.getChances();
	}
	
	/**
	 * return the selected word length
	 * @return the selected word length
	 */
	public static int getSelectedWordLength() {
		return gameAttributes.getSelectedWordLength();
	}
	
	/**
	 * Return the characters entered by the user
	 * @return the characters entered by the user
	 */
	public static String getCharactersUsed() {
		return gameAttributes.getCharacterUsed().toString();
	}
	
	/**
	 * Updates the characters used if character entered by the user does not exist
	 * @param enteredCharacter the character entered by user
	 */
	private static void updateCharactersUsed(char enteredCharacter) {
	
		if (gameAttributes.getCharacterUsed().toString().indexOf(enteredCharacter) == -1) {
			gameAttributes.getCharacterUsed().append(enteredCharacter);
		}
	}
	
	/**
	 * Update the chances remaining for the game.
	 */
	private static void updateRemainingChances() {
		gameAttributes.setChances(gameAttributes.getChances() - 1); 
	}
}
