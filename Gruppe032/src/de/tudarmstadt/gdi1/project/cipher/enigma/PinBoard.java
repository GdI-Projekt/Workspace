package de.tudarmstadt.gdi1.project.cipher.enigma;

/**
 * A pinboard is the representation of the mechanical pin board of the enigma.
 * It links letters to other letters and is the first and the last step of a
 * letter in the encryption and in the decryption process of the enigma.
 * 
 */
public interface PinBoard {
	/**
	 * passes the given character through the pinboard.
	 * 
	 * @param c
	 *            the character that should be passed through the pinboard.
	 * @return The translated Character at the end of the pinboard
	 */
	public char translate(char c);

}
