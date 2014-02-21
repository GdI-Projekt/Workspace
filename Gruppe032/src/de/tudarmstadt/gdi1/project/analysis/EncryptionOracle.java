package de.tudarmstadt.gdi1.project.analysis;

import de.tudarmstadt.gdi1.project.cipher.Cipher;

/**
 * 
 * Enryption oracle used for chosen plaintext attacks
 * 
 * @see ChosenPlaintextAnalysis
 */
public interface EncryptionOracle<C extends Cipher> {

	/**
	 * Given a plaintext this oracle returns the corresponding ciphertext
	 * 
	 * @param plaintext
	 *            the plaintext to encrypt
	 * @return the corresponding ciphertext
	 */
	public String encrypt(String plaintext);
}
