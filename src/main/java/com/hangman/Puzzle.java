package com.hangman;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

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

	public void prepare() {
		hideCharacters();
	}

	/**
	 * Hides the characters in the selected word depending on the value of
	 * characterToHide.
	 * 
	 */
	public void hideCharacters() {
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

	public boolean tryLetter(char letter) {
		if (checkCharacterValid(letter)) {
			updatePattern(letter);
			return true;
		} else {
			return false;
		}
	}

	public String getVisiblePattern() {
		return visiblePattern;
	}

	public boolean isSolved() {
		return visiblePattern.indexOf(UNDERSCORE) < 0;
	}

	private void updatePattern(char letter) {
		StringBuilder wordBuilder = new StringBuilder(word);
		StringBuilder patternBuilder = new StringBuilder(visiblePattern);
		int index = wordBuilder.indexOf(String.valueOf(letter));
		while (!isSolved() && index >= 0) {
			wordBuilder.setCharAt(index, UNDERSCORE);
			patternBuilder.setCharAt(index, letter);
			index = wordBuilder.indexOf(String.valueOf(letter));
		}
		visiblePattern = patternBuilder.toString();
		LOGGER.debug("Updated puzzle pattern: " + visiblePattern);
	}

	private boolean checkCharacterValid(char letter) {
		char[] charArray = visiblePattern.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == UNDERSCORE && word.charAt(i) == letter) {
				return true;
			}
		}
		return false;
	}

	public int getHiddenCharCount() {
		return word.length() - visibleCharCount;
	}
}
