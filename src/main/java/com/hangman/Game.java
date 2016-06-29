package com.hangman;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hangman.repository.WordRepository;

public class Game {

	private GameLevel gameLevel;
	private DisplayConsole displayConsole;
	private int remainingChances;
	private List<Character> enteredCharacters;
	private Puzzle puzzle;
	private static final Logger LOGGER = Logger.getLogger(Game.class);

	public Game(DisplayConsole displayConsole, GameLevel level) {
		this.displayConsole = displayConsole;
		this.gameLevel = level;
		this.enteredCharacters = new ArrayList<Character>();
	}

	public void start() {
		LOGGER.info("The Hangman Game Started...");
		displayConsole.init();
		displayConsole.displayBanner();
		String word = WordRepository.getInstance().guessWord(gameLevel);
		puzzle = new Puzzle(word, gameLevel.getVisibleCharCount());
		remainingChances = gameLevel.getWrongGuessAllowed();
		puzzle.initializePattern();
		while (!puzzle.isSolved() && remainingChances > 0) {
			displayConsole.displayGameInfo(gameLevel, enteredCharacters, puzzle.getVisiblePattern(), remainingChances);
			char userEnteredCharacter = displayConsole.scanCharacter();
			boolean valid = puzzle.tryWithCharacter(userEnteredCharacter);
			enteredCharacters.add(userEnteredCharacter);
			if (!valid) {
				remainingChances--;
			}
		}
		if (puzzle.isSolved()) {
			displayConsole.displayWinningMessage(word);
		} else {
			displayConsole.displayLosingMessage(word);
		}
		displayConsole.close();
	}

}
