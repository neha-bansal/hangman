package com.hangman;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hangman.repository.WordRepository;

public class Game {

	private GameLevel gameLevel;
	private String word;
	private DisplayConsole displayConsole;
	private int remainingChances;
	private List<Character> enteredCharacters = new ArrayList<Character>();
	private Puzzle puzzle;
	private static final Logger LOGGER = Logger.getLogger(Game.class);

	public Game(DisplayConsole displayConsole, GameLevel level) {
		this.displayConsole = displayConsole;
		this.gameLevel = level;
	}

	public void start() {
		LOGGER.info("The Hangman Game Started...");
		word = WordRepository.getInstance().guessWord(gameLevel);
		puzzle = new Puzzle(word, gameLevel.getVisibleCharCount());
		remainingChances = gameLevel.getWrongGuessAllowed()
				+ puzzle.getHiddenCharCount();
		puzzle.prepare();
		displayConsole.init();
		while (!puzzle.isSolved() && remainingChances > 0) {
			displayConsole.displayInfo(enteredCharacters,
					puzzle.getVisiblePattern(), remainingChances);
			char userEnteredCharacter = displayConsole.scanCharacter();
			puzzle.tryLetter(userEnteredCharacter);
			enteredCharacters.add(userEnteredCharacter);
			remainingChances--;
		}
		if (puzzle.isSolved()) {
			displayConsole.displayWinningMessage(word);
		} else {
			displayConsole.displayLossingMessage(word);
		}
		displayConsole.close();
	}

}
