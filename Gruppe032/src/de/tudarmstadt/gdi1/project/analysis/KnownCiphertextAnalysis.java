package de.tudarmstadt.gdi1.project.analysis;

import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;

public interface KnownCiphertextAnalysis {

	/**
	 * Attack to determine the used key based on a given ciphertext and a given
	 * distribution on the alphabet.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param distribution
	 *            the distribution
	 * @return the key, a part of the key, or null
	 */
	public Object knownCiphertextAttack(String ciphertext, Distribution distribution);

	/**
	 * Attack to determine the used key based on a ciphertext and a given
	 * dictionary.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param dictionary
	 *            the dictionary
	 * @return the key, a part of the key, or null
	 */
	public Object knownCiphertextAttack(String ciphertext, Dictionary dictionary);

	/**
	 * Attack to determine the used key based on a given ciphertext and a given
	 * distribution on the alphabet.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param dist
	 *            The distribution
	 * @param dict
	 *            the dictionary containing all used words in the plaintext
	 * @return the key, a part of the key, or null
	 */
	public Object knownCiphertextAttack(String ciphertext, Distribution distribution, Dictionary dict);

}
