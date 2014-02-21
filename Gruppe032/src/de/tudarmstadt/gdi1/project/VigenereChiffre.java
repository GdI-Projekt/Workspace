package de.tudarmstadt.gdi1.project;

/**
 * Polyalphabetisches Verschl�sseln nach Vigen�re.
 * @author Laura
 *
 */
public class VigenereChiffre extends Polyalphabetic {
	
	Alphabet source; //Ausgangsalphabet
	Alphabet[] dest; //Schl�sselalphabet-Array, welches im Konstruktor abh�ngig vom Keyword 
	//aufgebaut wird
	
	/**
	 * Erzeugt einen Vigenere.
	 * @param key der Schl�ssel (Keyword)
	 * @param alphabet das Ausgangsalphabet
	 */
	public VigenereChiffre(String key, Alphabet alphabet) {
		super(alphabet, (Alphabet[])null);
		
		char[] keyArray = key.toCharArray();
		
		dest = new Alphabet[keyArray.length];
		
		//Erstellung des dest-Arrays abh�ngig von den Buchstaben im Keyword:
		for (int i = 0; i < keyArray.length; i++) {
			int index = source.getIndex(keyArray[i]);
			Alphabet shiftedAlph = (Alphabet) source.shiftAlphabet(source, index);
			dest[i] = shiftedAlph;
		}
	}
	

}
