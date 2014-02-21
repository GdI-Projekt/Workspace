package de.tudarmstadt.gdi1.project.analysis.monoalphabetic;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.ValidateDecryptionOracle;

/**
 * Interface containing methods to guide the known ciphertext attack against
 * monoalphabetic ciphers using backtracking.
 * 
 * @see MonoalphabeticCribCryptanalysis
 */
public interface BacktrackingAnalysis {

	/**
	 * This methods marks the starting point of the recursion for a backtracking
	 * session
	 * 
	 * @param key
	 *            the current (partial) solution for the reconstructed key. An
	 *            assignment is stored as character from source alphabet maps to
	 *            character from target alphabet.
	 * 
	 * @param ciphertext
	 *            the known ciphertext
	 * 
	 * @param alphabet
	 *            the plaintext alphabet
	 * 
	 * @param distribution
	 *            the distribution
	 * 
	 * @param dictionary
	 *            a dictionary
	 * 
	 * @param cribs
	 *            a list of words contained in the plaintext
	 * 
	 * @param validateDecryptionOracle
	 *            An oracle that allows to test whether a reconstructed
	 *            plaintext is correct.
	 * @return
	 */
	Map<Character, Character> reconstructKey(Map<Character, Character> key,
			String ciphertext, Alphabet alphabet,
			Distribution distribution, Dictionary dictionary, List<String> cribs, ValidateDecryptionOracle validateDecryptionOracle);

	/**
	 * Returns a list of (sorted) assignments for the next
	 * 
	 * @param targetCharacter
	 *            the character from the source alphabet for which the
	 *            assignment is computed
	 * 
	 * @param key
	 *            the current partial key
	 * @param ciphertext
	 *            the ciphertext
	 * @param alphabet
	 *            the plaintext alphabet
	 * @param distribution
	 *            the distribution
	 * @param dictionary
	 *            a dictionary
	 * @return
	 */
	Collection<Character> getPotentialAssignments(Character targetCharacter, Map<Character, Character> key, String ciphertext,
			Alphabet alphabet,
			Distribution distribution,
			Dictionary dictionary);

	/**
	 * Returns the next character from the source alphabet that should be
	 * handled (that is for which a target character from the cipher alphabet
	 * should be chosen).
	 * 
	 * @param key
	 *            the current partial key
	 * @param alphabet
	 *            the plaintext alphabet
	 * @param distribution
	 *            the distribution
	 * @param dictionary
	 *            a dictionary
	 * @param cribs
	 *            a list of words contained in the plaintext
	 * @return a character from the alphabet
	 */
	Character getNextSourceChar(Map<Character, Character> key, Alphabet alphabet, Distribution distribution, Dictionary dictionary,
			List<String> cribs);

	/**
	 * The method is given a curent partial solution and checks whether this
	 * partial solution can lead to a correct soluttion or not.
	 * 
	 * 
	 * @param alphabet
	 *            the alphabet
	 * @param ciphertext
	 *            the ciphertext
	 * @param key
	 *            the current partial key
	 * @param distribution
	 *            the distribution
	 * @param dictionary
	 *            a dictionary
	 * @param cribs
	 *            a list of words contained in the plaintext
	 * @return true, if the current partial solution is consistent with a
	 *         correct solution
	 */
	boolean isPromisingPath(Alphabet alphabet, String ciphertext, Map<Character, Character> key, Distribution distribution,
			Dictionary dictionary, Collection<String> cribs);

}
