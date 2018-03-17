/**
 * 
 */
package net.digitaltsunami.wordnet.util;

import net.digitaltsunami.word.trie.filter.CharFilter;

/**
 * Char filter to change term separator ('_') in wordnet entries to a space. All
 * other values will be examined to determine if they are a letter as defined by
 * {@link Character#isLetter(char)}. Input failing that test will be skipped.
 * 
 * @author dhagberg
 * 
 */
public class WordNetCharFilter implements CharFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.digitaltsunami.word.trie.CharFilter#apply(char)
	 */
	public char apply(char input) {
		if (input == '_') {
			return ' ';
		} else {
			return Character.isLetter(input) ? input : CharFilter.SKIP_CHAR;
		}
	}
}
