package com.hangman.demo.config;

import java.util.ArrayList;
import java.util.List;

public class HangmanConfiguration {
	
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
	}
	
	public List<String> getWordsList() {
		return wordsList;
	}

	public void setWordsList(List<String> wordsList) {
		this.wordsList = wordsList;
	}

}
