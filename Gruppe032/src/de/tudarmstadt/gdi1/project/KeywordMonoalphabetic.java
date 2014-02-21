package de.tudarmstadt.gdi1.project;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Repr�sentiert die monoalphabetische Verschl�sselung mit einem Schl�sselwort.
 * @author Laura
 *
 */
public class KeywordMonoalphabetic extends de.tudarmstadt.gdi1.project.Monoalphabetic
implements de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.KeywordMonoalphabeticCipher {

	Alphabet source; //ausgehendes Alphabet
	Alphabet key; //Alphabet, in das verschl�sselt wird
	String keyword; //Passwort
	
	/**
	 * Konstruktor f�r ein KeywordMonoalphabeticCipher 
	 * @param source ausgehendes Alphabet
	 * @param key Schl�sselalphabet
	 * @param keyword Passwort
	 */
	public KeywordMonoalphabetic(Alphabet alphabet, String keyword) {
		super(alphabet, alphabet);
		this.keyword = keyword;
		key = alphabet;
		
		ArrayList<Character> keywordAlphSet = new ArrayList<>();
		char[] keywordArray = keyword.toCharArray();
		
		for (int i = 0; i < keywordArray.length; i++) {
			if (!(keywordAlphSet.contains(keywordArray[i]))) 
					keywordAlphSet.add(keywordArray[i]); //f�gt dem Set die Buchstaben des Keys hinzu 
		}
		
		char[] restAlphabet = key.reverseAlphabet(key).asCharArray();
		
		
		for (int i = 0; i < restAlphabet.length; i++) {
			if (!(keywordAlphSet.contains(restAlphabet[i]))) 
				keywordAlphSet.add(restAlphabet[i]); 
			//f�gt dem Set die restlichen Buchstaben des Alphabets hinzu, diese aber in umgekehrter 
			//Reihenfolge 
		}
		
		key = new Alphabet(keywordAlphSet); //das Schl�sselalphabet wird dem neu erzeugten
		//Alphabet aus Passwort und umgedrehten Alphabet gleichgesetzt
		
		super.key = key;
		
		
		
		}
	
	/**
	 * Liefert das key-Alphabet, in das verschl�sselt wird.
	 * @return das key-Alphabet (Schl�sselwort + umgekehrtes Alphabet)
	 */
	public Alphabet getKey() {
		return key;
	}
	
}