package de.tudarmstadt.gdi1.project.cipher.enigma;

public interface Rotor {

	/**
	 * passes a given character through the rotor of an enigma.
	 * 
	 * @param c
	 *            the character that should be passed through the rotor
	 * @param forward
	 *            true if we pass the character forward through the rotor.
	 *            Should be true before the ReverseRotor has been passed and
	 *            false afterwards.
	 * @return the translated character.
	 */
	public char translate(char c, boolean forward);

	/**
	 * rotates the rotor to its next position.
	 * 
	 * @return true if the rotor reached is intial position (i.e., the next
	 *         rotor has to be rotated), otherwise false
	 */
	public boolean rotate();

	/**
	 * resets the rotor to its default position
	 */
	public void reset();
}
