package com.hangman;

public class NoWordFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoWordFoundException(String msg) {
		super(msg);
	}
}
