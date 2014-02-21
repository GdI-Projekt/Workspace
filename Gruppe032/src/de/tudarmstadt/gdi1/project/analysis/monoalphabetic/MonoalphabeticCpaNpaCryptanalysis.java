package de.tudarmstadt.gdi1.project.analysis.monoalphabetic;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.analysis.ChosenPlaintextAnalysis;
import de.tudarmstadt.gdi1.project.analysis.EncryptionOracle;
import de.tudarmstadt.gdi1.project.analysis.KnownPlaintextAnalysis;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.MonoalphabeticCipher;

/**
 * 
 * Only the methods specified directly in this interface need to be implemented
 * 
 */
public interface MonoalphabeticCpaNpaCryptanalysis extends ChosenPlaintextAnalysis<MonoalphabeticCipher>, KnownPlaintextAnalysis {

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tudarmstadt.gdi1.project.analysis.KnownPlaintextAnalysis#
	 * knownPlaintextAttack(java.lang.String, java.lang.String,
	 * de.tudarmstadt.gdi1.project.alphabet.Alphabet)
	 */
	public char[] knownPlaintextAttack(String ciphertext, String plaintext, Alphabet alphabet);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.tudarmstadt.gdi1.project.analysis.ChosenPlaintextAnalysis#
	 * chosenPlaintextAttack
	 * (de.tudarmstadt.gdi1.project.analysis.EncryptionOracle,
	 * de.tudarmstadt.gdi1.project.alphabet.Alphabet)
	 */
	public char[] chosenPlaintextAttack(EncryptionOracle<MonoalphabeticCipher> oracle, Alphabet alphabet);
}
