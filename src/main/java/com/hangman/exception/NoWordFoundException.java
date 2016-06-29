package com.hangman.exception;

public class NoWordFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoWordFoundException(String msg) {
		super(msg);
	}
}
