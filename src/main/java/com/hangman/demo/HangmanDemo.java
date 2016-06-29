package com.hangman.demo;

import com.hangman.DisplayConsole;
import com.hangman.Game;
import com.hangman.GameLevel;

public class HangmanDemo {

	public static void main(String[] args) {
		Game game = new Game(new DisplayConsole(), GameLevel.MEDIUM);
		game.start();
	}
}
