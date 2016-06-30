package com.hangman;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Handles the responsibility of initializing and manipulating the pattern with hidden characters.
 *
 */
public class Puzzle {
	public static final char UNDERSCORE = '_';
	private static final Logger LOGGER = Logger.getLogger(Puzzle.class);
	private String word;
	private String visiblePattern;
	private int visibleCharCount;

	public Puzzle(String word, int visibleCharCount) {
		this.word = word;
		this.visibleCharCount = visibleCharCount;
	}

	/**
	 * Initialize the pattern with hidden characters using the actual guessed word.
	 * It firstly finds out the positions to hide and then creates a new pattern 
	 * with characters hidden at those positions.
	 */
	public void initializePattern() {
		Random random = new Random();
		Set<Integer> positionsToHideSet = new HashSet<>();
		int noOfChars = word.length();
		while (positionsToHideSet.size() < getHiddenCharCount()) {
			int choosenIndex = random.nextInt(noOfChars);
			positionsToHideSet.add(choosenIndex);
		}
		LOGGER.debug("Positions to hide in the selected word: "
				+ positionsToHideSet);
		Iterator<Integer> iterator = positionsToHideSet.iterator();
		StringBuilder updatedWordBuilder = new StringBuilder(word);
		while (iterator.hasNext()) {
			int charIndexToHide = iterator.next();
			updatedWordBuilder.setCharAt(charIndexToHide, UNDERSCORE);
		}
		visiblePattern = updatedWordBuilder.toString();
		LOGGER.debug("Puzzle pattern: " + visiblePattern);
	}

	/**
	 * Try the puzzle with user entered character to check if it is valid or not.
	 * @param character the user entered character
	 * @return whether character is valid or not
	 */
	public boolean tryWithCharacter(char character) {
		if (checkCharacterValid(character)) {
			updatePattern(character);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the pattern
	 * @return the pattern
	 */
	public String getVisiblePattern() {
		return visiblePattern;
	}

	/**
	 * Checks whether the puzzle is solved or not
	 * @return whether the puzzle is solved or not
	 */
	public boolean isSolved() {
		return visiblePattern.indexOf(UNDERSCORE) < 0;
	}

	/**
	 * Updates the pattern if the user entered character is valid.
	 * @param character the user entered character
	 */
	private void updatePattern(char character) {
		StringBuilder wordBuilder = new StringBuilder(word);
		StringBuilder patternBuilder = new StringBuilder(visiblePattern);
		int index = wordBuilder.indexOf(String.valueOf(character));
		while (!isSolved() && index >= 0) {
			wordBuilder.setCharAt(index, UNDERSCORE);
			patternBuilder.setCharAt(index, character);
			index = wordBuilder.indexOf(String.valueOf(character));
		}
		visiblePattern = patternBuilder.toString();
		LOGGER.debug("Updated puzzle pattern: " + visiblePattern);
	}

	/**
	 * Checks whether the user entered character is valid or not.
	 * @param character the user entered character
	 * @return whether the user entered character is valid or not
	 */
	private boolean checkCharacterValid(char character) {
		char[] charArray = visiblePattern.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == UNDERSCORE && word.charAt(i) == character) {
				return true;
			}
		}
		return false;
	}

	private int getHiddenCharCount() {
		return word.length() - visibleCharCount;
	}
}
