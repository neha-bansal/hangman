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

	public boolean tryWithCharacter(char character) {
		if (checkCharacterValid(character)) {
			updatePattern(character);
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
