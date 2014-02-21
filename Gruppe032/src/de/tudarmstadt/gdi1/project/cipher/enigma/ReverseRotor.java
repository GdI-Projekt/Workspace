package de.tudarmstadt.gdi1.project.cipher.enigma;

public interface ReverseRotor {
	/**
	 * passes the given character through the ReverseRotor of an enigma.
	 * 
	 * @param c
	 *            the character that should be encrypted
	 * @return the encrypted character
	 */
	public char translate(char c);
}
