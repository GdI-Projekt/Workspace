package de.tudarmstadt.gdi1.project.alphabet;

import java.util.List;

/**
 * A distribution handles letters and n-grams and their frequency.
 * 
 */
public interface Distribution {

	/**
	 * retrieve all the ngrams of the given length from all the learned strings,
	 * sorted by their frequency
	 * 
	 * @param length
	 *            the ngram length, so 1 means only a character 2 stands for
	 *            bigrams and so on.
	 * @return a descending sorted list that contains all the ngrams sorted by
	 *         their frequency
	 */
	public List<String> getSorted(int length);

	/**
	 * Gets the frequency to a given key. If the key is longer than the created
	 * ngrams or if the key was never seen the frequency is 0.
	 * 
	 * @param key
	 *            the character, bigram, trigram,... we want the frequency for
	 * @return the frequency of the given character, bigram, trigram,... in all
	 *         the learned texts
	 */
	double getFrequency(String key);

	/**
	 * 
	 * @return the alphabet of the distribution
	 */
	Alphabet getAlphabet();

	/**
	 * retrieves the string with its learned frequency from the distribution, by
	 * its size and frequency rank.
	 * 
	 * @param length
	 *            the size of the ngram
	 * @param rank
	 *            the rank where we want to look at (1 = highest rank)
	 * @return the ngram of the given size that is on the given rank in its
	 *         distribution or null if the ngramsize is bigger than the maximum
	 *         learned ngram size or the rank is higher than the number of
	 *         learned ngrams
	 */
	public String getByRank(int length, int rank);
}
