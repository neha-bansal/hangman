package com.hangman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hangman.example.utility.HangmanUtility;

public class Game {
	public static final char UNDERSCORE = '_';
	private GameLevel gameLevel;
	private String word;
	private String pattern;
	private DisplayConsole displayConsole;
	private int remainingChances;
	private List<Character> enteredCharacters = new ArrayList<Character>();

	private static final Logger LOGGER = Logger.getLogger(Game.class);

	public Game(DisplayConsole displayConsole, GameLevel level) {
		this.displayConsole = displayConsole;
		this.gameLevel = level;
	}

	public void start() {
		LOGGER.info("The Hangman Game Started...");
		word = WordRepository.getInstance().guessWord(gameLevel);
		remainingChances = gameLevel.getWrongGuessAllowed() + getHiddenCharCount();
		hideCharacters();
		while (isPatternCompleted() && remainingChances <= 0) {
			displayConsole.displayInfo(enteredCharacters, pattern, remainingChances);
			char userEnteredCharacter = displayConsole.scanCharacter();
			remainingChances--;
			// printBorder();
			if (checkCharacterValid(userEnteredCharacter)) {
				updatePattern(userEnteredCharacter);
			}
		}
		if (isPatternCompleted()) {
			displayConsole.displayWinningMessage(word);
		} else {
			displayConsole.displayLossingMessage(word);
		}
	}

	/**
	 * It hides the characters in the selected word depending on the value of
	 * characterToHide.
	 * 
	 * @return the updated word with hidden characters
	 */
	public String hideCharacters() {
		Random random = new Random();
		Set<Integer> positionsToHideSet = new HashSet<>();
		for (int i = 0; i < getHiddenCharCount(); i++) {
			positionsToHideSet.add(random.nextInt(word.length()));
		}
		LOGGER.debug("Positions to hide in the selected word: " + positionsToHideSet);
		Iterator<Integer> iterator = positionsToHideSet.iterator();
		StringBuilder updatedWordBuilder = new StringBuilder(word);

		while (iterator.hasNext()) {
			int characterPlace = iterator.next();
			updatedWordBuilder.setCharAt(characterPlace, UNDERSCORE);
		}
		pattern = updatedWordBuilder.toString();
		LOGGER.debug("Updated word after hiding the characters: " + pattern);
		return updatedWordBuilder.toString();
	}

	/**
	 * It checks that the character entered by the user is a valid character or
	 * not, i.e., it will be used in the updated word.
	 * 
	 * @param enteredCharacter
	 *            the character entered by the user
	 * @return whether entered character is valid or not.
	 */
	public boolean checkCharacterValid(char enteredCharacter) {
		return word.indexOf(enteredCharacter) >= 0;
	}

	public int getHiddenCharCount() {
		return word.length() - gameLevel.getVisibleCharCount();
	}

	public void updatePattern(char enteredCharacter) {
		StringBuilder w = new StringBuilder(word);
		StringBuilder p = new StringBuilder(pattern);
		int index = w.indexOf(String.valueOf(enteredCharacter));
		while (!isPatternCompleted() && index >= 0) {
			w.setCharAt(index, UNDERSCORE);
			p.setCharAt(index, enteredCharacter);
			index = w.indexOf(String.valueOf(enteredCharacter));
		}
		pattern = p.toString();
		LOGGER.debug("Updated word after hiding the characters: " + pattern);
	}

	public boolean isPatternCompleted() {
		return pattern.indexOf(UNDERSCORE) < 0;
	}
}
