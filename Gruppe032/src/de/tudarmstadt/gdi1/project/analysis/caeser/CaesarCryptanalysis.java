package de.tudarmstadt.gdi1.project.analysis.caeser;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.KnownCiphertextAnalysis;
import de.tudarmstadt.gdi1.project.analysis.KnownPlaintextAnalysis;

public interface CaesarCryptanalysis extends KnownCiphertextAnalysis, KnownPlaintextAnalysis {

	@Override
	Integer knownCiphertextAttack(String ciphertext, Distribution distribution);

	@Override
	Integer knownCiphertextAttack(String ciphertext, Dictionary dictionary);

	@Override
	Integer knownCiphertextAttack(String ciphertext, Distribution distribution, Dictionary dict);

	@Override
	Integer knownPlaintextAttack(String ciphertext, String plaintext, Alphabet alphabet);

	@Override
	Integer knownPlaintextAttack(String ciphertext, String plaintext, Distribution distribution);

	@Override
	Integer knownPlaintextAttack(String ciphertext, String plaintext, Distribution distribution, Dictionary dictionary);
}
