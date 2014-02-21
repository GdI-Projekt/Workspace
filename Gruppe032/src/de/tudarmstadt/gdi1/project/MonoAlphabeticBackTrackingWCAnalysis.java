package de.tudarmstadt.gdi1.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.ValidateDecryptionOracle;

/**
 * Repr�sentiert einen Angreifer, der nur den Chiffr�text vor sich liegen hat und 
 * unterst�tzt wird von Cribs (= bekannte W�rter im Plaintext).
 * @author Laura
 *
 */
public class MonoAlphabeticBackTrackingWCAnalysis implements 
de.tudarmstadt.gdi1.project.analysis.monoalphabetic.MonoalphabeticCribCryptanalysis, 
de.tudarmstadt.gdi1.project.analysis.monoalphabetic.BacktrackingAnalysis {

	/**
	 * Ermittelt mittels Backtracking & Rekursion den Schl�ssel f�r das monoalphabetische 
	 * Verschl�sselungsverfahren.
	 * @param key Teill�sung
	 * @param ciphertext Chiffr�text
	 * @param alphabet Quellalphabet
	 * @param distribution H�ufigkeitsanalyse
	 * @param dictionary ein W�rterbuch
	 * @param cribs Liste bekannter W�rter aus dem Klartext
	 * @param validateDecryptionOracle Hilfsmittel, um zu �berpr�fen, ob der ermittelte 
	 * Schl�ssel richtig ist
	 * @return (Teil-)L�sung
	 */
	@Override
	public Map<Character, Character> reconstructKey(
			Map<Character, Character> key, String ciphertext,
			Alphabet alphabet, Distribution distribution,
			Dictionary dictionary, List<String> cribs,
			ValidateDecryptionOracle validateDecryptionOracle) {
		
		char[] klartextzeichen = key.keySet().toArray().toString().toCharArray();
		char[] schluessel = key.values().toArray().toString().toCharArray();
		
		//�berpr�fung, ob ein vollst�ndiger Schl�ssel vorliegt:
		if (klartextzeichen.length == schluessel.length) {
			de.tudarmstadt.gdi1.project.Alphabet keyAlph = new de.tudarmstadt.gdi1.project.Alphabet(key.values());
			de.tudarmstadt.gdi1.project.Alphabet sourceAlph = new de.tudarmstadt.gdi1.project.Alphabet(key.keySet());
			Monoalphabetic decrypter = new de.tudarmstadt.gdi1.project.Monoalphabetic(keyAlph, sourceAlph);
			
			char[] cipherArray = ciphertext.toCharArray();
			char[] decrypted = new char[cipherArray.length];
			
			//Chiffr� wird mit dem gefundenen Schl�ssel entschl�sselt
			for (int i = 0; i < cipherArray.length; i++) {
				decrypted[i] = decrypter.reverseTranslate(cipherArray[i], 0);
			}
			
			String decryptedText = decrypted.toString();
			
			//Pr�fe, ob der so entstandene Klartext mit dem tats�chlichen Klartext 
			//�bereinstimmt:
			if (validateDecryptionOracle.isCorrect(decryptedText)) return key;
			
			//Backtracking-Fall:
			else {
				key.remove(schluessel[schluessel.length-1]);
				de.tudarmstadt.gdi1.project.Alphabet newAlphabet = (de.tudarmstadt.gdi1.project.Alphabet) alphabet;
				newAlphabet.removeChar(schluessel[schluessel.length-1]);
				
				List<Character> potentialAssignments = (List<Character>) getPotentialAssignments(' ', key, ciphertext,
						newAlphabet, distribution, dictionary);
				
				key.put(klartextzeichen[klartextzeichen.length-1], potentialAssignments.get(0));
				
				return reconstructKey(key, ciphertext, alphabet, distribution, dictionary, 
						cribs, validateDecryptionOracle);
			}
		}
		
		char nextSourceChar = getNextSourceChar(key, alphabet, distribution, dictionary, cribs);
		List<Character> potentialAssignments = (List<Character>) getPotentialAssignments(' ', key, ciphertext,
				alphabet, distribution, dictionary);
		
		key.put(nextSourceChar, potentialAssignments.get(0));
		
		return reconstructKey(key, ciphertext, alphabet, distribution, dictionary, cribs, 
				validateDecryptionOracle);
	}

	/**
	 * Gibt eine Liste von Zeichen aus, welche noch nicht im Zielalphabet der Teill�sung 
	 * enthalten sind.
	 * @param targetCharacter wird nicht benutzt
	 * @param key Teill�sung
	 * @param ciphertext Chiffr�text
	 * @param alphabet Quellalphabet
	 * @param distribution H�ufigkeitsanalyse
	 * @param dictionary ein W�rterbuch
	 * @return Liste noch nicht verwendeter Zeichen in Zielalphabet der Teill�sung
	 */
	@Override
	public Collection<Character> getPotentialAssignments(
			Character targetCharacter, Map<Character, Character> key,
			String ciphertext, Alphabet alphabet, Distribution distribution,
			Dictionary dictionary) {
		
		List<Character> result = new ArrayList<>();
		char[] usedChars = key.keySet().toArray().toString().toCharArray();
		char[] alph = alphabet.asCharArray();
		
		for (int i = 0; i < alph.length; i++) {
			if (!(key.containsValue(alph[i]))) result.add(alph[i]); 
		}
		
		return result;
	}

	/**
	 * Gibt ein Zeichen aus dem Quellalphabet aus, welches noch nicht in der Teill�sung 
	 * auftauchte.
	 * @param key Teill�sung 
	 * @param alphabet Quellalphabet
	 * @param distribution H�ufigkeitsanalyse
	 * @param dictionary ein W�rterbuch
	 * @param cribs bekannte W�rter des Klartextes
	 * @return Buchstabe des Quellalphabets, der noch nicht in der Teill�sung vorkam
	 */
	@Override
	public Character getNextSourceChar(Map<Character, Character> key,
			Alphabet alphabet, Distribution distribution,
			Dictionary dictionary, List<String> cribs) {
		char[] alph = alphabet.asCharArray();
		for (int i = 0; i < alph.length; i++) {
			if (!(key.containsKey(alph[i]))) return alph[i];
		}
		
		return null;
	}

	@Override
	public boolean isPromisingPath(Alphabet alphabet, String ciphertext,
			Map<Character, Character> key, Distribution distribution,
			Dictionary dictionary, Collection<String> cribs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public char[] knownCiphertextAttack(String ciphertext,
			Distribution distribution, Dictionary dictionary, List<String> cribs) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Der Angreifer kennt den Chiffr�text und einige W�rter im Klartext.
	 * @param ciphertext gegebener Schl�sseltext
	 * @param distribution H�ufigkeitsanalyse auf dem Quell-Alphabet
	 * @param dictionary ein W�rterbuch
	 * @param cribs Liste mit W�rtern, die im Klartext vorkommen 
	 * @param validateDecryptionOracle Hilfsobjekt zur �berpr�fung richtiger Entschl�sselung
	 */
	@Override
	public char[] knownCiphertextAttack(String ciphertext,
			Distribution distribution, Dictionary dictionary,
			List<String> cribs,
			ValidateDecryptionOracle validateDecryptionOracle) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gibt Zwischenstand der Berechnung aus.
	 * @param sourceAlphabet Ausgangsalphabet (des Klartextes)
 * @param targetKey zu suchender Schl�ssel
 * 
	 */
	@Override
	public String getState(Alphabet sourceAlphabet, Alphabet targetKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
