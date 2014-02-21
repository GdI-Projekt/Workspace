package de.tudarmstadt.gdi1.project.cipher;

/**
 * General interface for all ciphers. Supports encryption and decryption.
 *
 */
public interface Cipher {
	
	/**
	 * Encrypt a text according to the encryption method of the cipher
	 * @param text the plaintext to encrypt
	 * @return the encrypted plaintext (=ciphertext)
	 */
	public String encrypt(String text);
	
	/**
	 * Decrypt a text according to the decryption method of the cipher
	 * @param text the ciphertext to decrypt
	 * @return the decrypted ciphertext (=plaintext)
	 */
	public String decrypt(String text);
}
