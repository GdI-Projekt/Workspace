package de.tudarmstadt.gdi1.project.alphabet;

/**
 * A dictionary is a collection of valid words. A word is the character sequence
 * that stands between a space and/or one of the following characters: ',' '!'
 * '?' '.'<br \>
 * A word is valid if it contains only characters that are part of the given
 * alphabet.
 * 
 */
public interface Dictionary extends Iterable<String> {

	/**
	 * Checks if a word is contained in the dictionary
	 * 
	 * @param word
	 *            the word
	 * @return true, if the word is contained in the dictionary, otherwise false
	 */
	public boolean contains(String word);

	/**
	 * 
	 * @return the Alphabet that defines the characterspace of the dictionary
	 */
	public Alphabet getAlphabet();

	/**
	 * 
	 * @return the number of entries in the dictionary
	 */
	public int size();

	/**
	 * gets an item at a specific position (sorted in natural order) in the
	 * dictionary
	 * 
	 * @param index
	 *            the index of the item that should be retrieved.
	 * @return the item at the index. If the index is out of bounds an
	 *         indexOutOfBounds exception is thrown
	 */
	public String get(int index);

}
