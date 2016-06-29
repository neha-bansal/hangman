package com.hangman.example.domain;

/**
 * This class represents the required attributes for the game at a particular point of time.
 */
public class GameAttributes {

	private String selectedWord;
	private String updatedWord;
	private int selectedWordLength;
	private StringBuilder characterUsed = new StringBuilder();
	private int charactersToHideCount;
	private int chances = 10;
	
	public String getSelectedWord() {
		return selectedWord;
	}
	
	public void setSelectedWord(String selectedWord) {
		this.selectedWord = selectedWord;
	}
	
	public String getUpdatedWord() {
		return updatedWord;
	}
	
	public void setUpdatedWord(String updatedWord) {
		this.updatedWord = updatedWord;
	}
	
	public int getSelectedWordLength() {
		return selectedWordLength;
	}
	
	public void setSelectedWordLength(int selectedWordLength) {
		this.selectedWordLength = selectedWordLength;
	}
	
	public StringBuilder getCharacterUsed() {
		return characterUsed;
	}
	
	public void setCharacterUsed(StringBuilder characterUsed) {
		this.characterUsed = characterUsed;
	}
	
	public int getCharactersToHideCount() {
		return charactersToHideCount;
	}
	
	public void setCharactersToHideCount(int charactersToHideCount) {
		this.charactersToHideCount = charactersToHideCount;
	}

	public int getChances() {
		return chances;
	}

	public void setChances(int chances) {
		this.chances = chances;
	}
}
