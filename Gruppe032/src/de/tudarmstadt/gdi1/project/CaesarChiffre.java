package de.tudarmstadt.gdi1.project;




import de.tudarmstadt.gdi1.project.Alphabet; //shiftAlphabet ist in Alphabet implementiert
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.Caesar;

/**
 * 
 * @author Stephanie, Laura, Thorsten
 *
 */
public class CaesarChiffre extends Monoalphabetic implements Caesar {
	int key; //Zahl um die das Ausgangsalphabet verschoben werden soll
	
	/**
	 * Konstruktor Caesar Chiffre
	 * @param source Alphabet des Ausgangstextes
	 * @param key Zahl um die das Alphabet Verschoben wird
	 */
	public CaesarChiffre(Alphabet source, int key) {
		super(null, source);
		super.key = (Alphabet) source.shiftAlphabet(source, key);
	}
	
	public Alphabet getDest(){
		return super.key;
	}
	
}
