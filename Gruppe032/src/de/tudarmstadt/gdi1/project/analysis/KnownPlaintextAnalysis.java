package de.tudarmstadt.gdi1.project.analysis;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;

public interface KnownPlaintextAnalysis {

	/**
	 * Attack to determine the used key based on a given cipher- and
	 * (corresponding) plaintext.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param plaintext
	 *            the corresponding plaintext
	 * @param alphabet
	 *            the alphabet
	 * @return the key, a part of the key, or null
	 */
	public Object knownPlaintextAttack(String ciphertext, String plaintext, Alphabet alphabet);

	/**
	 * Attack to determine the used key based on a given cipher- and
	 * (corresponding) plaintext and a given distribution on the alphabet.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param plaintext
	 *            the corresponding plaintext
	 * @param distribution
	 *            the distribution
	 * @return the key, a part of the key, or null
	 */
	public Object knownPlaintextAttack(String ciphertext, String plaintext, Distribution distribution);

	/**
	 * Attack to determine the used key based on a given cipher- and
	 * (corresponding) plaintexts and a given distribution on the alphabet.
	 * 
	 * @param ciphertexts
	 *            the ciphertext
	 * @param plaintexts
	 *            the corresponding plaintext
	 * @param distribution
	 *            The distribution
	 * @param dict
	 *            the dictionary containing all used words in the plaintext
	 * @return the key, a part of the key, or null
	 */
	public Object knownPlaintextAttack(String ciphertext, String plaintext, Distribution distribution, Dictionary dictionary);
}
