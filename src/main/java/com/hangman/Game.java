package com.hangman;

import org.apache.log4j.Logger;

public class Game {
	private GameLevel gameLevel;

	private String key;

	private DisplayConsole displayConsole;
	private static final Logger LOGGER = Logger.getLogger(Game.class);

	public Game(DisplayConsole displayConsole, GameLevel level) {
		this.displayConsole = displayConsole;
		this.gameLevel = level;
	}

	public void start() {
		LOGGER.info("The Hangman Game Started...");
	}

}
