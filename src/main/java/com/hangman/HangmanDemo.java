package com.hangman;

public class HangmanDemo {

	public static void main(String[] args) {
		Game game = new Game(new DisplayConsole(), GameLevel.MEDIUM);
		game.start();
	}
}
