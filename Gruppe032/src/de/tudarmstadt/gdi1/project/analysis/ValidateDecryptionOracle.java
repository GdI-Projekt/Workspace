package de.tudarmstadt.gdi1.project.analysis;

import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;

/**
 * 
 * This object is an oracle used in the cryptoanalysis to test whether a
 * reconstructed plaintext is the correct plaintext.
 */
public interface ValidateDecryptionOracle {

	/**
	 * Given a plaintext this method returns true, if that plaintext is the
	 * "correct" plaintext. Depending on the implementation the definition of
	 * "correct" varies. A "cheating" oracle might know the plaintext one is
	 * after. This is great for testing and debugging. An actual implementation
	 * would need to make use of a {@link Distribution} and a {@link Dictionary}
	 * to determine whether a plaintext is "good".
	 * 
	 * @param plaintext
	 *            the plaintext to test
	 * @return true if the plaintext is the correct plaintext
	 */
	public boolean isCorrect(String plaintext);

}
