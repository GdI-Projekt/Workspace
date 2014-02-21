package de.tudarmstadt.gdi1.project.analysis.vigenere;

import java.util.List;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.KnownPlaintextAnalysis;

public interface VigenereCryptanalysis extends KnownPlaintextAnalysis {

	/**
	 * Attack to determine all possible length of the used key based on a given
	 * ciphertext.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @return the possible key lengths (in ascending order)
	 */
	List<Integer> knownCiphertextAttack(String ciphertext);

	@Override
	String knownPlaintextAttack(String ciphertext, String plaintext, Alphabet alphabet);

	/**
	 * Attack to determine the used key based on a given ciphertext, a given
	 * distribution on the alphabet and a list of known plaintext cribs.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param distribution
	 *            the distribution
	 * @param cribs
	 *            the list of substrings known to appear in the plaintext
	 * 
	 * @return the key, a part of the key, or null
	 */
	String knownCiphertextAttack(String ciphertext, Distribution distribution, List<String> cribs);

}
