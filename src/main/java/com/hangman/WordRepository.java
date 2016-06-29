package com.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class WordRepository {

	private static final Logger LOGGER = Logger.getLogger(WordRepository.class);

	private List<String> wordsList = Arrays.asList(new String[] { "january", "fuzzy", "adventure", "holiday", "quizzing", "puzzling", "jeopardizing", "lexicalizing", "technical", "wednesday",
			"interaction", "ireland", "hangman", "gliders", "scientific", "remote" });

	private Map<Integer, List<String>> wordsMap = new HashMap<Integer, List<String>>();

	private static final WordRepository INSTANCE = new WordRepository();

	private WordRepository() {
		for (String word : wordsList) {
			Integer wordLength = word.length();
			if (!wordsMap.containsKey(wordLength)) {
				List<String> list = new ArrayList<>();
				list.add(word);
				wordsMap.put(wordLength, list);
			} else {
				List<String> list = wordsMap.get(wordLength);
				list.add(word);
			}
		}
		LOGGER.info(wordsMap);
	}

	public String guessWord(GameLevel level) {
		int lengthOfWordsToSelect = guessNumber(level.getMinCharCount(), level.getMaxCharCount());
		LOGGER.info("Finding word with length: " + lengthOfWordsToSelect);
		List<String> wordsList = wordsMap.get(lengthOfWordsToSelect);
		if (wordsList == null || wordsList.isEmpty()) {
			throw new NoWordFoundException("No word found in repository for the specified game level !!!");
		}
		String guessedWord = wordsList.get((int) (Math.random() * (wordsList.size()) - 1));
		LOGGER.info("Word guessed from the list: " + guessedWord);
		return guessedWord;
	}

	public static WordRepository getInstance() {
		return INSTANCE;
	}

	private int guessNumber(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1) + min);
	}

	public static void main(String[] args) {
		System.err.println(new WordRepository().guessNumber(5, 7));
		;
	}

}
