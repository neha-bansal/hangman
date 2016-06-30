package com.hangman;

/**
 * Represents the different game levels.
 *
 */
public enum GameLevel {

	EASY(3, 4, 2, 4), MEDIUM(5, 7, 2, 4), HARD(8, 12, 3, 4);

	private int minCharCount;
	private int maxCharCount;
	private int visibleCharCount;
	private int wrongGuessAllowed;

	private GameLevel(int minCharCount, int maxCharCount, int visibleCharCount, int wrongGuessAllowed) {
		this.minCharCount = minCharCount;
		this.maxCharCount = maxCharCount;
		this.visibleCharCount = visibleCharCount;
		this.wrongGuessAllowed = wrongGuessAllowed;
	}

	public int getMinCharCount() {
		return minCharCount;
	}

	public int getMaxCharCount() {
		return maxCharCount;
	}

	public int getVisibleCharCount() {
		return visibleCharCount;
	}

	public int getWrongGuessAllowed() {
		return wrongGuessAllowed;
	}

}
