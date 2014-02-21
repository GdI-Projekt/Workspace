package de.tudarmstadt.gdi1.project;

import java.util.Collection;
import java.util.List;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.ValidateDecryptionOracle;
import de.tudarmstadt.gdi1.project.analysis.caeser.CaesarCryptanalysis;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.Individual;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.MonoalphabeticCpaNpaCryptanalysis;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.MonoalphabeticCribCryptanalysis;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.MonoalphabeticKnownCiphertextCryptanalysis;
import de.tudarmstadt.gdi1.project.analysis.vigenere.VigenereCryptanalysis;
import de.tudarmstadt.gdi1.project.cipher.enigma.Enigma;
import de.tudarmstadt.gdi1.project.cipher.enigma.PinBoard;
import de.tudarmstadt.gdi1.project.cipher.enigma.ReverseRotor;
import de.tudarmstadt.gdi1.project.cipher.enigma.Rotor;
import de.tudarmstadt.gdi1.project.cipher.substitution.SubstitutionCipher;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.Caesar;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.KeywordMonoalphabeticCipher;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.MonoalphabeticCipher;
import de.tudarmstadt.gdi1.project.cipher.substitution.polyalphabetic.PolyalphabeticCipher;
import de.tudarmstadt.gdi1.project.cipher.substitution.polyalphabetic.Vigenere;
import de.tudarmstadt.gdi1.project.utils.Utils;

/**
 * TODO: Denote, when paramters should be final, unchangable values (e.g.,
 * Alphabet) -- i.e., implementing those via some empty constructor +
 * setParameter method should be considered wrong.
 */
public interface Factory {

	/**
	 * Constructs a {@link Distribution} from the given text for all ngrams of
	 * size 1 to ngramsize. Only characters available in the alphabet should be
	 * taken into consideration {@link Alphabet#normalize(String)}.
	 * 
	 * @param source
	 *            the alphabet
	 * @param text
	 *            the text to base the distribution on
	 * @param ngramsize
	 *            the maximum n-gram size
	 * @return a distribution object
	 */
	public Distribution getDistributionInstance(Alphabet source, String text, int ngramsize);

	/**
	 * Constructs an {@link Alphabet} based on the (ordered) collection of
	 * characters
	 * 
	 * @param characters
	 *            an ordered collection of characters
	 * 
	 * @return an alphabet based on the given collection of characters
	 */
	public Alphabet getAlphabetInstance(Collection<Character> characters);

	/**
	 * Loads all valid words from the input into the dictionary. <br \>
	 * A word is the character sequence that stands between a space and/or one
	 * of the following characters: ',' '!' '?' '.'<br \>
	 * A word is valid if it contains only characters that are part of the given
	 * alphabet.
	 * 
	 * @param alphabet
	 *            the source alphabet
	 * @param text
	 *            the text where the words should be extracted from
	 */
	public Dictionary getDictionaryInstance(Alphabet alphabet, String text);

	/**
	 * Constructs a {@link MonoalphabeticCipher} mapping from a source alphabet
	 * to a target alphabet.
	 * 
	 * @param source
	 *            the source alphabet
	 * @param dest
	 *            the destination (target) alphabet
	 * @return
	 */
	public MonoalphabeticCipher getMonoalphabeticCipherInstance(Alphabet source, Alphabet dest);

	/**
	 * Constructs a Caesar cipher over the given alphabet and with a shift
	 * specified by key.
	 * 
	 * @param key
	 *            the shift
	 * @param alphabet
	 *            the alphabet
	 * @return
	 */
	public Caesar getCaesarInstance(int key, Alphabet alphabet);

	/**
	 * Constructs a {@link KeywordMonoalphabeticCipher} over the given alphabet
	 * and with the given keyword
	 * 
	 * @param key
	 *            the keyword
	 * @param alphabet
	 *            the alphabet
	 * @return
	 */
	public KeywordMonoalphabeticCipher getKeywordMonoalphabeticCipherInstance(String key, Alphabet alphabet);

	/**
	 * Constructs a generic {@link PolyalphabeticCipher} from one source
	 * alphabet and at least a single target alphabet.
	 * 
	 * @param source
	 *            the source alphabet
	 * @param dest
	 *            an arary (vararg) of target alphabets
	 * @return
	 */
	public PolyalphabeticCipher getPolyalphabeticCipherInstance(Alphabet source, Alphabet... dest);

	/**
	 * Constructs a {@link Vigenere} Ciphere for a given key and alphabet
	 * 
	 * @param key
	 *            the key
	 * @param alphabet
	 *            the alphabet
	 * @return
	 */
	public Vigenere getVigenereCipherInstance(String key, Alphabet alphabet);

	/**
	 * Returns an isntance of a {@link CaesarCryptanalysis}.
	 * 
	 * @return
	 */
	public CaesarCryptanalysis getCaesarCryptanalysisInstance();

	/**
	 * Returns an instance of {@link MonoalphabeticCpaNpaCryptanalysis}.
	 * 
	 * @return
	 */
	public MonoalphabeticCpaNpaCryptanalysis getMonoalphabeticCpaNpaCryptanalysis();

	/**
	 * Returns an instance of {@link MonoalphabeticCribCryptanalysis}.
	 * 
	 * @return
	 */
	public MonoalphabeticCribCryptanalysis getMonoalphabeticCribCryptanalysisInstance();

	/**
	 * returns an instance of {@link MonoalphabeticKnownCiphertextCryptanalysis}
	 * .
	 * 
	 * @return
	 */
	public MonoalphabeticKnownCiphertextCryptanalysis getMonoalphabeticKnownCiphertextCryptanalysisInstance();

	/**
	 * returns an instance of {@link VigenereCryptanalysis}
	 * 
	 * 
	 * @return
	 */
	public VigenereCryptanalysis getVigenereCryptanalysisInstance();

	/**
	 * returns an instance of {@link Utils}.
	 * 
	 * @return
	 */
	public Utils getUtilsInstance();

	/**
	 * Constructs an {@link Enigma} with the given rotors, pinboard and a
	 * {@link ReverseRotor}.
	 * 
	 * @param rotors
	 *            The (ordered) list of rotors
	 * @param pinboard
	 *            the pinboard
	 * @param reverseRotor
	 *            the reverse rotor
	 * @return
	 */
	public Enigma getEnigmaInstance(List<Rotor> rotors, PinBoard pinboard, ReverseRotor reverseRotor);

	/**
	 * Constructs a {@link PinBoard} from a source alphabet and a destiniation
	 * alphabet
	 * 
	 * @param source
	 *            the input alphabet
	 * @param destination
	 *            the mapping of the output
	 * @return
	 */
	public PinBoard getPinBoardInstance(Alphabet source, Alphabet destination);

	/**
	 * Constructs a rotor from an two alphabets (ingoing and exit) and a
	 * position.
	 * 
	 * @param entryAlph
	 * @param exitAlph
	 * @param startPosition
	 * @return
	 */
	public Rotor getRotorInstance(Alphabet entryAlph, Alphabet exitAlph, int startPosition);

	/**
	 * Constructs a reverse rotor from two alphabets (ingoing and exit).
	 * 
	 * @param entryAlph
	 * @param exitAlph
	 * @return
	 */
	public ReverseRotor getReverseRotatorInstance(Alphabet entryAlph, Alphabet exitAlph);

	/**
	 * 
	 * @return The class implementing {@link SubstitutionCipher}
	 */
	public Class<? extends SubstitutionCipher> getAbstractSubstitutionCipherClass();

	/**
	 * Constructs a {@link ValidateDecryptionOracle} from a distribution and a
	 * dictionary
	 * 
	 * @param distribution
	 * @param dictionary
	 * @return
	 */
	public ValidateDecryptionOracle getValidateDecryptionOracle(Distribution distribution, Dictionary dictionary);

	/**
	 * Constructs an {@link Individual}
	 * 
	 * @param alphabet
	 * @param fitness
	 * @return
	 */
	public Individual getIndividualInstance(Alphabet alphabet, double fitness);
}
