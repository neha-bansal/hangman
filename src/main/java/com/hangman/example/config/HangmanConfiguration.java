package com.hangman.example.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * This class is used for the basic configuration required for the game.
 *
 */
public class HangmanConfiguration {
	
	private static Logger logger = Logger.getLogger(HangmanConfiguration.class);
	private List<String> wordsList = new ArrayList<>();
	
	public HangmanConfiguration() {
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
		logger.info("Game configuration is completed.");
		logger.info("Size of the words' list: " + wordsList.size()); 
	}
	
	public List<String> getWordsList() {
		return wordsList;
	}

	public void setWordsList(List<String> wordsList) {
		this.wordsList = wordsList;
	}

}
