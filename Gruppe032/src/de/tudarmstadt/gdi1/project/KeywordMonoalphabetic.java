package de.tudarmstadt.gdi1.project;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Repräsentiert die monoalphabetische Verschlüsselung mit einem Schlüsselwort.
 * @author Laura
 *
 */
public class KeywordMonoalphabetic extends de.tudarmstadt.gdi1.project.Monoalphabetic
implements de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.KeywordMonoalphabeticCipher {

	Alphabet source; //ausgehendes Alphabet
	Alphabet key; //Alphabet, in das verschlüsselt wird
	String keyword; //Passwort
	
	/**
	 * Konstruktor für ein KeywordMonoalphabeticCipher 
	 * @param source ausgehendes Alphabet
	 * @param key Schlüsselalphabet
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
					keywordAlphSet.add(keywordArray[i]); //fügt dem Set die Buchstaben des Keys hinzu 
		}
		
		char[] restAlphabet = key.reverseAlphabet(key).asCharArray();
		
		
		for (int i = 0; i < restAlphabet.length; i++) {
			if (!(keywordAlphSet.contains(restAlphabet[i]))) 
				keywordAlphSet.add(restAlphabet[i]); 
			//fügt dem Set die restlichen Buchstaben des Alphabets hinzu, diese aber in umgekehrter 
			//Reihenfolge 
		}
		
		key = new Alphabet(keywordAlphSet); //das Schlüsselalphabet wird dem neu erzeugten
		//Alphabet aus Passwort und umgedrehten Alphabet gleichgesetzt
		
		super.key = key;
		
		
		
		}
	
	/**
	 * Liefert das key-Alphabet, in das verschlüsselt wird.
	 * @return das key-Alphabet (Schlüsselwort + umgekehrtes Alphabet)
	 */
	public Alphabet getKey() {
		return key;
	}
	
}
