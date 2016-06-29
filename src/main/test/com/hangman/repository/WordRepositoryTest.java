package com.hangman.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hangman.GameLevel;
import com.hangman.repository.WordRepository;

/**
 * Unit test class for word repository class.
 */
public class WordRepositoryTest {
	
	private WordRepository wordRepository = WordRepository.getInstance();

	@Test
	public void testGuessWordWithGameLevelMedium() {
		GameLevel gameLevel = GameLevel.MEDIUM;
		int minCharCount = gameLevel.getMinCharCount();
		int maxCharCount = gameLevel.getMaxCharCount();
		
		String guessedWord = wordRepository.guessWord(gameLevel);
		
		assertTrue("Word length should be >= " + minCharCount, guessedWord.length() >= minCharCount);
		assertTrue("Word length should be <= " + maxCharCount, guessedWord.length() <= maxCharCount);
	}
	
	@Test
	public void testGuessWordWithGameLevelHard() {
		GameLevel gameLevel = GameLevel.HARD;
		int minCharCount = gameLevel.getMinCharCount();
		int maxCharCount = gameLevel.getMaxCharCount();
		
		String guessedWord = wordRepository.guessWord(gameLevel);
		
		assertTrue("Word length should be >= " + minCharCount, guessedWord.length() >= minCharCount);
		assertTrue("Word length should be <= " + maxCharCount, guessedWord.length() <= maxCharCount);
	}
	
	@Test
	public void testGuessWordWithGameLevelEasy() {
		GameLevel gameLevel = GameLevel.EASY;
		int minCharCount = gameLevel.getMinCharCount();
		int maxCharCount = gameLevel.getMaxCharCount();
		
		String guessedWord = wordRepository.guessWord(gameLevel);
		
		assertTrue("Word length should be >= " + minCharCount, guessedWord.length() >= minCharCount);
		assertTrue("Word length should be <= " + maxCharCount, guessedWord.length() <= maxCharCount);
	}
	
	@Test
	public void testGuessNumberWithGameLevelMedium() {
		GameLevel gameLevel = GameLevel.MEDIUM;
		int minCharCount = gameLevel.getMinCharCount();
		int maxCharCount = gameLevel.getMaxCharCount();
		
		int guessedNumber = wordRepository.guessNumber(minCharCount, maxCharCount);
		
		assertTrue("Guessed number should be >= " + minCharCount, guessedNumber >= minCharCount);
		assertTrue("Guessed number should be <= " + maxCharCount, guessedNumber <= maxCharCount);
	}
	
	@Test
	public void testGuessNumberWithGameLevelHard() {
		GameLevel gameLevel = GameLevel.HARD;
		int minCharCount = gameLevel.getMinCharCount();
		int maxCharCount = gameLevel.getMaxCharCount();
		
		int guessedNumber = wordRepository.guessNumber(minCharCount, maxCharCount);
		
		assertTrue("Guessed number should be >= " + minCharCount, guessedNumber >= minCharCount);
		assertTrue("Guessed number should be <= " + maxCharCount, guessedNumber <= maxCharCount);
	}
	
	@Test
	public void testGuessNumberWithGameLevelEasy() {
		GameLevel gameLevel = GameLevel.EASY;
		int minCharCount = gameLevel.getMinCharCount();
		int maxCharCount = gameLevel.getMaxCharCount();
		
		int guessedNumber = wordRepository.guessNumber(minCharCount, maxCharCount);
		
		assertTrue("Guessed number should be >= " + minCharCount, guessedNumber >= minCharCount);
		assertTrue("Guessed number should be <= " + maxCharCount, guessedNumber <= maxCharCount);
	}
}
