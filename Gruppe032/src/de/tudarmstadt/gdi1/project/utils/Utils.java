package de.tudarmstadt.gdi1.project.utils;

import java.util.List;
import java.util.Map;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;

public interface Utils {

	/**
	 * transforms a text into a pretty printable format. This means that the
	 * text has in every line 6 space separated blocks with 10 characters each.
	 * 
	 * So
	 * 
	 * <pre>
	 * loremipsumdolorsitametconsecteturadipiscingelitvivamusquismassaestnuncelitelitdictumvelligulaiddapibuspretiumrisuscrasineuismodnisinu
	 * ncpharetradiamelitiaculishendreritnisitincidunteunullamfeugiatfermentumantequissuscipitestvehiculasitametnuncnonvehiculaenimduisatlib
	 * eroquisduidapibusfermentumuteuduinullamrutrumgravidadolorvelullamcorperleofermentumeuinliberovelitaccumsanvelpulvinarnecsagittisetmet
	 * usnullaanequevitaesemmalesuadaplaceratutegestasmetus
	 * </pre>
	 * 
	 * will be
	 * 
	 * <pre>
	 * loremipsum dolorsitam etconsecte turadipisc ingelitviv amusquisma
	 * ssaestnunc elitelitdi ctumvellig ulaiddapib uspretiumr isuscrasin 
	 * euismodnis inuncphare tradiameli tiaculishe ndreritnis itincidunt 
	 * eunullamfe ugiatferme ntumantequ issuscipit estvehicul asitametnu 
	 * ncnonvehic ulaenimdui satliberoq uisduidapi busferment umuteuduin 
	 * ullamrutru mgravidado lorvelulla mcorperleo fermentume uinliberov 
	 * elitaccums anvelpulvi narnecsagi ttisetmetu snullaaneq uevitaesem 
	 * malesuadap laceratute gestasmetu s
	 * </pre>
	 * 
	 * @param ciphertext
	 *            the text that should be pretty formated
	 * @return the pretty formatted text
	 */
	String toDisplay(String ciphertext);

	/**
	 * Divides a string into ngrams of the given lengths
	 * 
	 * @param text
	 *            the text that should be devided
	 * @param lengths
	 *            the lengths of the ngrams we need
	 * @return lists that contain all the ngrams of a fixed size. The lists are
	 *         maped to their ngram size in the result map.
	 */
	Map<Integer, List<String>> ngramize(String text, int... lengths);

	/**
	 * Returns the given alphabet shifted by pos positions to the left.
	 * 
	 * @param alphabet
	 *            the alphabet
	 * @param shift
	 *            the number of positions to shift
	 * @return the new shifted alphabet
	 */
	Alphabet shiftAlphabet(Alphabet alphabet, int shift);

	/**
	 * Returns the given alphabet in reverse order (a,b,c,...,x,y,z) ->
	 * (z,y,x,...,c,b,a).
	 * 
	 * @param alphabet
	 *            the alphabet
	 * 
	 * @return a new alphabet with the same characters but in reverse order
	 */
	Alphabet reverseAlphabet(Alphabet alphabet);

	/**
	 * Checks if the given alphabets contain the same characters. This means
	 * they are a permutation of each other.
	 * 
	 * @param alphabet1
	 *            the first alphabet
	 * @param alphabet2
	 *            the second alphabet
	 * @return if the alphabets are a permutation of each other
	 */
	boolean containsSameCharacters(Alphabet alphabet1, Alphabet alphabet2);

	/**
	 * Given an alphabet, the method returns a new alphabet with characters
	 * randomly shuffled.
	 * 
	 * @param alphabet
	 *            the source alphabet
	 * 
	 * @return a new alphabet containing the same characters as the source
	 *         alphabet but in a random order.
	 */
	Alphabet randomizeAlphabet(Alphabet alphabet);

}
