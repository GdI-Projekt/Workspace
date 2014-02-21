package de.tudarmstadt.gdi1.project.analysis.monoalphabetic;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.KnownCiphertextAnalysis;
import de.tudarmstadt.gdi1.project.analysis.ValidateDecryptionOracle;

/**
 * 
 * Only the methods specified directly in this interface need to be implemented
 */
public interface MonoalphabeticKnownCiphertextCryptanalysis extends KnownCiphertextAnalysis {

	/**
	 * Attack to determine the used key based on a ciphertext, distribution and
	 * a dictionary.
	 * 
	 * @param ciphertext
	 * @param distribution
	 * @param dictionary
	 * @return
	 */
	char[] knownCiphertextAttack(String ciphertext, Distribution distribution, Dictionary dictionary);

	/**
	 * Attack to determine the used key based on a ciphertext, distribution and
	 * a dictionary. In addition an oracle is provided that allows to verify
	 * whether a decryption is the correct one.
	 * 
	 * @param ciphertext
	 * @param distribution
	 * @param dictionary
	 * @param validateDecryptionOracle
	 * @return
	 */
	char[] knownCiphertextAttack(String ciphertext, Distribution distribution, Dictionary dictionary,
			ValidateDecryptionOracle validateDecryptionOracle);

	/**
	 * Returns a description of the current state of the algorithm
	 * 
	 * @param sourceAlphabet
	 * @param targetKey
	 * @return a description of the current state
	 */
	String getState(Alphabet sourceAlphabet, Alphabet targetKey);

}
