package com.hangman;

public class NoWordFoundException extends RuntimeException{

	public NoWordFoundException(String msg) {
		super(msg);
	}
}
