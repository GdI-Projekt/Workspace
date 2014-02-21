package de.tudarmstadt.gdi1.project.alphabet;

/**
 * An Alphabet is an ordered collection of unique characters.
 * 
 */
public interface Alphabet extends Iterable<Character> {

	/**
	 * Searches for a character in the alphabet
	 * 
	 * @param chr
	 *            the character to find
	 * @return the position of given character in the alphabet (0-based), or -1
	 *         if character is not in alphabet
	 */
	int getIndex(char chr);

	/**
	 * Retrieves a character from the alphabet
	 * 
	 * @param index
	 *            the position (0-based) of the character to retrieve
	 * @return the character on position index in the alphabet
	 */
	char getChar(int index);

	/**
	 * @return the number of characters in the alphabet
	 */
	int size();

	/**
	 * Checks if the given character is in the alphabet
	 * 
	 * @param chr
	 *            the character that should be checked
	 * @return true if the character is in the alphabet
	 */
	boolean contains(char chr);

	/**
	 * Checks if the given string contains only characters that are allowed in
	 * the alphabet
	 * 
	 * @param word
	 *            the string that should be checked
	 * @return true if the word contains only allowed characters
	 */
	boolean allows(String word);

	/**
	 * Normalizes the given string. This means deleting all the characters that
	 * are not part of the alphabet.
	 * 
	 * @param input
	 *            the string that should be normalized.
	 * @return the normalized string
	 */
	String normalize(String input);

	/**
	 * Returns the underlying characters in correct order as a char array of
	 * size {@link #size()}
	 * 
	 * @return a char array representing the alphabet
	 */
	char[] asCharArray();

}
