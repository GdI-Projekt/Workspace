package de.tudarmstadt.gdi1.project.cipher.substitution;

import de.tudarmstadt.gdi1.project.cipher.Cipher;

/**
 * General Interface for all Substitution Ciphers. Substitution cyphers encrypt
 * and decrypt text by substituting single characters.
 * 
 */
public interface SubstitutionCipher extends Cipher {

	/**
	 * Translates the given character that is on the given position in the text
	 * into its encrypted equivalent.
	 * 
	 * @param chr
	 *            the character that needs to be translated
	 * @param i
	 *            the position the character stands in the text
	 * @return the translated/encrypted character
	 */
	public char translate(char chr, int i);

	/**
	 * translates the given character that is on the given position in the text
	 * back into its decrypted equivalent
	 * 
	 * @param chr
	 *            the character that needs to be reversetranslated
	 * @param i
	 *            the position of the character in the text
	 * @return the reversetranslated/decrypted character
	 */
	public char reverseTranslate(char chr, int i);

}
