package com.hangman.exception;

/**
 * Exception class to be used if no word is found in the words repository depending on the game level.
 *
 */
public class NoWordFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoWordFoundException(String msg) {
		super(msg);
	}
}
