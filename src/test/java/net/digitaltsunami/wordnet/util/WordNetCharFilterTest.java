package net.digitaltsunami.wordnet.util;

import static org.junit.Assert.*;

import net.digitaltsunami.word.trie.filter.CharFilter;
import net.digitaltsunami.word.trie.filter.LetterCharFilter;

import org.junit.Test;

public class WordNetCharFilterTest {
	private WordNetCharFilter filter = new WordNetCharFilter();

	@Test
	public void testApplyWithLetter() {
		assertEquals('a', filter.apply('a'));
	}
	@Test
	public void testApplyWithSeparator() {
		assertEquals(' ', filter.apply('_'));
	}
	@Test
	public void testApplyWithNumber() {
		assertEquals(CharFilter.SKIP_CHAR, filter.apply('1'));
	}

}
