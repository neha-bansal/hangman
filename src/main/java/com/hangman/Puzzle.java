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

	public String prepare() {
		hideCharacters();
		return visiblePattern;
	}

	public void hideCharacters() {
		Random random = new Random();
		Set<Integer> positionsToHideSet = new HashSet<>();
		for (int i = 0; i < getHiddenCharCount(); i++) {
			positionsToHideSet.add(random.nextInt(word.length()));
		}
		LOGGER.debug("Positions to hide in the selected word: "
				+ positionsToHideSet);
		Iterator<Integer> iterator = positionsToHideSet.iterator();
		StringBuilder updatedWordBuilder = new StringBuilder(word);

		while (iterator.hasNext()) {
			int characterPlace = iterator.next();
			updatedWordBuilder.setCharAt(characterPlace, UNDERSCORE);
		}
		visiblePattern = updatedWordBuilder.toString();
		LOGGER.debug("Updated word after hiding the characters: "
				+ visiblePattern);
	}

	public void tryLetter(char letter) {
		if (checkCharacterValid(letter)) {
			updatePattern(letter);
		}
	}

	public void updatePattern(char letter) {
		StringBuilder w = new StringBuilder(word);
		StringBuilder p = new StringBuilder(visiblePattern);
		int index = w.indexOf(String.valueOf(letter));
		while (!isCompleted() && index >= 0) {
			w.setCharAt(index, UNDERSCORE);
			p.setCharAt(index, letter);
			index = w.indexOf(String.valueOf(letter));
		}
		visiblePattern = p.toString();
		LOGGER.debug("Updated word after hiding the characters: "
				+ visiblePattern);
	}

	public boolean isCompleted() {
		return visiblePattern.indexOf(UNDERSCORE) < 0;
	}

	public boolean checkCharacterValid(char letter) {
		return word.indexOf(letter) >= 0;
	}

	public int getHiddenCharCount() {
		return word.length() - visibleCharCount;
	}
}
