package de.tudarmstadt.gdi1.project;

import de.tudarmstadt.gdi1.project.Alphabet;
import de.tudarmstadt.gdi1.project.Substitution;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.MonoalphabeticCipher;
import de.tudarmstadt.gdi1.project.exception.InvalidAlphabetException;

/**
 * Monoalphabetic implementiert MonoalphabeticCipher und erbt von Substitution
 * Die Methoden translate und reverseTranslate werden passend für eine Monoalphabetische Substitution
 * implementiert
 * @author Stephanie
 *
 */
public class Monoalphabetic extends Substitution implements MonoalphabeticCipher {

	Alphabet source; // Alphabet von dem man ausgeht
	Alphabet key; // Alphabet in das verschlüsselt wird
	
	@Override
	public char translate(char chr, int i) {
		int index = source.getIndex(chr); // wirft ArrayIndexOutOfBoundsException wenn chr nicht im Alphabet enthalten ist
		return key.getChar(index);
		// Die Position des Buchstaben im Text spielt keine Rolle
	}
	
	@Override
	public char reverseTranslate(char chr, int i) {
		int index = key.getIndex(chr);  //wirft ArrayIndexOutOfBoundsException wenn chr nicht im key enthalten ist
		return source.getChar(index);
		// Die Position des Buchstaben im Text spielt keine Rolle
	}
	
	/**
	 * Konstruktor, wirft Exception wenn key und source nicht gleich lang sind
	 * @param key Alphabet in das verschlüsselt wird
	 * @param source Alphabet des Ausgangstextes
	 */
	public Monoalphabetic(Alphabet key, Alphabet source){
		try{
			if(!(key.size() == source.size())) 
				throw new InvalidAlphabetException();
		}
		catch(InvalidAlphabetException e){
			System.out.println("Die Alphabete haben nicht die selbe Länge!");
		}
		this.key = key;
		this.source = source;
	}
}