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
 * Repräsentiert einen Angreifer, der nur den Chiffrétext vor sich liegen hat und 
 * unterstützt wird von Cribs (= bekannte Wörter im Plaintext).
 * @author Laura
 *
 */
public class MonoAlphabeticBackTrackingWCAnalysis implements 
de.tudarmstadt.gdi1.project.analysis.monoalphabetic.MonoalphabeticCribCryptanalysis, 
de.tudarmstadt.gdi1.project.analysis.monoalphabetic.BacktrackingAnalysis {

	/**
	 * Ermittelt mittels Backtracking & Rekursion den Schlüssel für das monoalphabetische 
	 * Verschlüsselungsverfahren.
	 * @param key Teillösung
	 * @param ciphertext Chiffrétext
	 * @param alphabet Quellalphabet
	 * @param distribution Häufigkeitsanalyse
	 * @param dictionary ein Wörterbuch
	 * @param cribs Liste bekannter Wörter aus dem Klartext
	 * @param validateDecryptionOracle Hilfsmittel, um zu überprüfen, ob der ermittelte 
	 * Schlüssel richtig ist
	 * @return (Teil-)Lösung
	 */
	@Override
	public Map<Character, Character> reconstructKey(
			Map<Character, Character> key, String ciphertext,
			Alphabet alphabet, Distribution distribution,
			Dictionary dictionary, List<String> cribs,
			ValidateDecryptionOracle validateDecryptionOracle) {
		
		char[] klartextzeichen = key.keySet().toArray().toString().toCharArray();
		char[] schluessel = key.values().toArray().toString().toCharArray();
		
		//Überprüfung, ob ein vollständiger Schlüssel vorliegt:
		if (klartextzeichen.length == schluessel.length) {
			de.tudarmstadt.gdi1.project.Alphabet keyAlph = new de.tudarmstadt.gdi1.project.Alphabet(key.values());
			de.tudarmstadt.gdi1.project.Alphabet sourceAlph = new de.tudarmstadt.gdi1.project.Alphabet(key.keySet());
			Monoalphabetic decrypter = new de.tudarmstadt.gdi1.project.Monoalphabetic(keyAlph, sourceAlph);
			
			char[] cipherArray = ciphertext.toCharArray();
			char[] decrypted = new char[cipherArray.length];
			
			//Chiffré wird mit dem gefundenen Schlüssel entschlüsselt
			for (int i = 0; i < cipherArray.length; i++) {
				decrypted[i] = decrypter.reverseTranslate(cipherArray[i], 0);
			}
			
			String decryptedText = decrypted.toString();
			
			//Prüfe, ob der so entstandene Klartext mit dem tatsächlichen Klartext 
			//übereinstimmt:
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
	 * Gibt eine Liste von Zeichen aus, welche noch nicht im Zielalphabet der Teillösung 
	 * enthalten sind.
	 * @param targetCharacter wird nicht benutzt
	 * @param key Teillösung
	 * @param ciphertext Chiffrétext
	 * @param alphabet Quellalphabet
	 * @param distribution Häufigkeitsanalyse
	 * @param dictionary ein Wörterbuch
	 * @return Liste noch nicht verwendeter Zeichen in Zielalphabet der Teillösung
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
	 * Gibt ein Zeichen aus dem Quellalphabet aus, welches noch nicht in der Teillösung 
	 * auftauchte.
	 * @param key Teillösung 
	 * @param alphabet Quellalphabet
	 * @param distribution Häufigkeitsanalyse
	 * @param dictionary ein Wörterbuch
	 * @param cribs bekannte Wörter des Klartextes
	 * @return Buchstabe des Quellalphabets, der noch nicht in der Teillösung vorkam
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
	 * Der Angreifer kennt den Chiffrétext und einige Wörter im Klartext.
	 * @param ciphertext gegebener Schlüsseltext
	 * @param distribution Häufigkeitsanalyse auf dem Quell-Alphabet
	 * @param dictionary ein Wörterbuch
	 * @param cribs Liste mit Wörtern, die im Klartext vorkommen 
	 * @param validateDecryptionOracle Hilfsobjekt zur Überprüfung richtiger Entschlüsselung
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
 * @param targetKey zu suchender Schlüssel
 * 
	 */
	@Override
	public String getState(Alphabet sourceAlphabet, Alphabet targetKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
