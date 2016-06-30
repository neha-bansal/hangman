package com.hangman;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hangman.repository.WordRepository;

/**
 * Represents the running game. It delegates the different responsibilities 
 * to different components of the application.
 */
public class Game {

	private GameLevel gameLevel;
	private DisplayConsole displayConsole;
	private int remainingChances;
	private List<Character> enteredCharacters;
	private Puzzle puzzle;
	private String word;
	private static final Logger LOGGER = Logger.getLogger(Game.class);

	public Game(DisplayConsole displayConsole, GameLevel level) {
		this.displayConsole = displayConsole;
		this.gameLevel = level;
		this.enteredCharacters = new ArrayList<Character>();
	}

	/**
	 * Starts the game by setting up the console and puzzle.
	 * 
	 */
	public void start() {
		LOGGER.info("The Hangman Game started...");
		displayConsole.init();
		displayConsole.displayBanner();
		
		word = WordRepository.getInstance().guessWord(gameLevel);
		puzzle = new Puzzle(word, gameLevel.getVisibleCharCount());
		remainingChances = gameLevel.getWrongGuessAllowed();
		puzzle.initializePattern();
	}
	
	/**
	 * Accepts the user input and try it with the puzzle until puzzle is solved or 
	 * no chances remaining.
	 * 
	 */
	public void progress() {
		while (!puzzle.isSolved() && remainingChances > 0) {
			displayConsole.displayGameInfo(gameLevel, enteredCharacters, puzzle.getVisiblePattern(), remainingChances);
			char userEnteredCharacter = displayConsole.scanCharacter();
			boolean valid = puzzle.tryWithCharacter(userEnteredCharacter);
			enteredCharacters.add(userEnteredCharacter);
			if (!valid) {
				remainingChances--;
			}
		}
	}
	
	/**
	 * Checks whether the puzzle is solved or not. Displays message on the console accordingly.
	 */
	public void completed() {
		if (puzzle.isSolved()) {
			displayConsole.displayWinningMessage(word);
		} else {
			displayConsole.displayLosingMessage(word);
		}
	}
	
	/**
	 * Stops the game by closing the console.
	 * 
	 */
	public void stop() {
		displayConsole.close();
		LOGGER.info("The Hangman Game stopped...");
	}
}
