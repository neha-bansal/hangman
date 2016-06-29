package com.hangman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordRepository {
	private List<String> wordsList = Arrays.asList(new String[] { "january", "adventure", "holiday", "technical", "wednesday", "interaction", "ireland", "hangman", "gliders", "scientific" });

	private Map<Integer, String[]> map = new HashMap<Integer, String[]>();

	public WordRepository() {
		// TODO build map based of word repository
	}

	public String guessWord(GameLevel level) {
		// TODO returned guessed word, use random number to generate number
		// between min,max of level then retrive list from map and choose one
		// from list, if not found throw exception
		return "";
	}
}
