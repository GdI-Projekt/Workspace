package de.tudarmstadt.gdi1.project.analysis.monoalphabetic;

import java.util.List;
import java.util.Random;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;

/**
 * Interface containing methods to guide the known ciphertext attack against
 * monoalphabetic ciphers using a genetic algorithm
 * 
 * @see MonoalphabeticKnownCiphertextCryptanalysis
 */
public interface GeneticAnalysis {

	/**
	 * Constructs the initial generation of size populationSize.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param alphabet
	 *            the alphabet
	 * @param distribution
	 *            the distribution
	 * @param populationSize
	 *            the population
	 * @return
	 */
	List<Individual> prepareInitialGeneration(String ciphertext, Alphabet alphabet, Distribution distribution, int populationSize);

	/**
	 * Given the list of survivors and a target size, this method computes the
	 * next generation.
	 * 
	 * @param survivors
	 *            the list of survivors
	 * @param populationSize
	 *            the size of the next generation
	 * @param random
	 *            an instance of {@link Random} for generating randomness
	 * @param alphabet
	 *            the alphabet
	 * @param distribution
	 *            the distribution
	 * @param dictionary
	 *            the dictionary
	 * @return
	 */
	List<Individual> generateNextGeneration(List<Individual> survivors, int populationSize, Random random, Alphabet alphabet,
			Distribution distribution, Dictionary dictionary);

	/**
	 * Given the current generation this method computes the list of survivors.
	 * 
	 * @param ciphertext
	 *            the ciphertext
	 * @param alphabet
	 *            the alphabet
	 * @param generation
	 *            the current generation
	 * @param distribution
	 *            the distribution
	 * @param dictionary
	 *            the dictionary
	 * @param nrOfSurvivors
	 *            the number of survivors
	 * @return a list containing nrOfSurvivors many members from the generation
	 */
	List<Individual> computeSurvivors(String ciphertext, Alphabet alphabet, List<Individual> generation,
			Distribution distribution, Dictionary dictionary, int nrOfSurvivors);

	/**
	 * Given an individual this method computes its fitness.
	 * 
	 * @param individual
	 *            the individual
	 * @param ciphertext
	 *            the ciphertext
	 * @param alphabet
	 *            the alphabet
	 * @param distribution
	 *            the distribution
	 * @param dictionary
	 *            the dictionary
	 * @return
	 */
	double computeFitness(Individual individual, String ciphertext, Alphabet alphabet, Distribution distribution,
			Dictionary dictionary);

}
