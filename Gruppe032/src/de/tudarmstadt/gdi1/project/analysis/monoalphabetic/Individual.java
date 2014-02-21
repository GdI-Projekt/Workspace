package de.tudarmstadt.gdi1.project.analysis.monoalphabetic;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;

/**
 * 
 * Represents an individual in the generation of a genetic algorithm
 * 
 * @see MonoalphabeticKnownCiphertextCryptanalysis
 */
public interface Individual {

	/**
	 * 
	 * @return this individual's alphabet
	 */
	Alphabet getAlphabet();

	/**
	 * 
	 * @return the individual's fitness
	 */
	double getFitness();
}
