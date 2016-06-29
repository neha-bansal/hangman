package com.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

//TODO make singleton
public class WordRepository {

	private static Logger logger = Logger.getLogger(WordRepository.class);

	private List<String> wordsList = Arrays.asList(new String[] { "january", "adventure", "holiday", "technical", "wednesday", "interaction", "ireland", "hangman", "gliders", "scientific" });

	private Map<Integer, List<String>> wordsMap = new HashMap<Integer, List<String>>();

	private static final WordRepository INSTANCE = new WordRepository();

	private WordRepository() {
		List<String> list;

		for (String word : wordsList) {
			Integer wordLength = word.length();
			if (!wordsMap.containsKey(wordLength)) {
				list = new ArrayList<>();
				list.add(word);
				wordsMap.put(wordLength, list);
			} else {
				list = wordsMap.get(wordLength);
				list.add(word);
			}
		}
	}

	public String guessWord(GameLevel level) {

		String guessedWord;
		int lengthOfWordsToSelect = (int) (Math.random() * ((level.getMaxCharCount() - level.getMinCharCount()) + 1) + level.getMinCharCount());

		List<String> wordsList = wordsMap.get(lengthOfWordsToSelect);

		if (wordsList.isEmpty()) {
			throw new NoWordFoundException("No word found in repository for the specified game level !!!");
		} else {
			guessedWord = wordsList.get((int) (Math.random() * (wordsList.size()) - 1));
		}
		logger.info("Word guessed from the list: " + guessedWord);

		return guessedWord;
	}

	public static WordRepository getInstance() {
		return INSTANCE;
	}
}
