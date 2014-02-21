package de.tudarmstadt.gdi1.project.analysis;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.cipher.Cipher;

/**
 * Specifies a chosen-plaintext attack
 * 
 * @param <C>
 */
public interface ChosenPlaintextAnalysis<C extends Cipher> {

	/**
	 * Attack to determine the used key based given an oracle that allows to
	 * encrypt arbitrary plaintexts.
	 * 
	 * @param oracle
	 *            An object that allows to generate arbitrary encryptions
	 * 
	 * @param alphabet
	 *            The plaintext alphabet
	 * @return the key or null if the key could not be recoverd
	 */
	public Object chosenPlaintextAttack(EncryptionOracle<C> oracle, Alphabet alphabet);
}
