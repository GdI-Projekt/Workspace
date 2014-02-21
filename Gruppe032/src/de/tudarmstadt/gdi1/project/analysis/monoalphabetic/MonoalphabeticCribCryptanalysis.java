package de.tudarmstadt.gdi1.project.analysis.monoalphabetic;

import java.util.List;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.ValidateDecryptionOracle;

/**
 * 
 * Only the methods specified directly in this interface need to be implemented
 */
public interface MonoalphabeticCribCryptanalysis {

	/**
	 * Attack to determine the used key based on a ciphertext and a given
	 * distribution and dictionary as well as a list of words that appear in the
	 * plaintext (cribs).
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param distribution
	 *            the distribution
	 * @param dictionary
	 *            the dictionary
	 * @param cribs
	 *            A list of words known to be in the plaintext
	 * @return The reconstructed key represented as a char array
	 */
	char[] knownCiphertextAttack(String ciphertext, Distribution distribution, Dictionary dictionary, List<String> cribs);

	/**
	 * Attack to determine the used key based on a ciphertext and a given
	 * distribution and dictionary as well as a list of words that appear in the
	 * plaintext (cribs). In addition an oracle is provided that allows to
	 * verify whether a decryption is the correct one.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param distribution
	 *            the distribution
	 * @param dictionary
	 *            the dictionary
	 * @param cribs
	 *            A list of words known to be in the plaintext
	 * @param validateDecryptionOracle
	 *            a verification oracle allowing to validate a decryption.
	 * @return The reconstructed key represented as a char array
	 */
	char[] knownCiphertextAttack(String ciphertext, Distribution distribution, Dictionary dictionary, List<String> cribs,
			ValidateDecryptionOracle validateDecryptionOracle);

	/**
	 * Returns a description of the current state of the algorithm
	 * 
	 * @param sourceAlphabet
	 * @param targetKey
	 * @return a description of the current state.
	 */
	String getState(Alphabet sourceAlphabet, Alphabet targetKey);

}
