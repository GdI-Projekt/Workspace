package de.tudarmstadt.gdi1.project;
import java.util.*;

import de.tudarmstadt.gdi1.project.utils.*;

import de.tudarmstadt.gdi1.project.exception.*;

/**
 * Repr�sentiert ein Alphabet.
 * @author Laura, Stephanie
 *
 */
public class Alphabet implements Iterable<Character>, de.tudarmstadt.gdi1.project.alphabet.Alphabet,
Utils {

	ArrayList<Character> alphabet;
	
	/**
	 * Erzeugt ein Alphabet.	
	 * @param alphabet das Alphabet
	 */
	public Alphabet(Collection<Character> alphabet) 
		{
			this.alphabet = new ArrayList<>();
			createAlphabet(alphabet);
		}

	/**
	 * Erzeugt das Standard-Alphabet von a bis z.
	 */
	public Alphabet() {
		this.alphabet = new ArrayList<>();
		char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for (int i = 0; i < abc.length; i++) {
			alphabet.add(abc[i]);
		}
	}
	
	/**
	 * Wandelt das Alphabet in ein Char-Array um.
	 * @return Alphabet als Char-Array
	 */
	public char[] asCharArray() {
		char[] result = new char[alphabet.size()];
		
		Iterator<Character> it = alphabet.iterator();
		int index = 0;
		
		while (it.hasNext()) {
			result[index] = it.next();
			index++;
		}
		
		return result;
	}

		/**
		 * Setzt das aktuelle Alphabet auf das �bergebene Alphabet.
		 * @param alphabet ein �bergebenes Alphabet
		 * @throws Exception Fehler bei doppeltem Buchstaben im Array
		 */
		public void createAlphabet(Collection<Character> alphabet) {
			
			Iterator<Character> it = alphabet.iterator();
			boolean doubleCharUsed = false;
			
			while (it.hasNext()) {
				char toAdd = it.next();
				if (this.alphabet.contains(toAdd)) {
					doubleCharUsed = true;
					break;
				}
				else this.alphabet.add(toAdd);
			}
			
			try { 	
					if (doubleCharUsed) throw new InvalidCharacterException();
			}
			
			catch(InvalidCharacterException dbe) { //gibt Fehlermeldung aus
				System.out.println("Illegales Alphabet: Doppelte Buchstaben enthalten!");
				this.alphabet.clear();
			}
				
		}
		
		
		/**
		 * �berpr�ft an welcher Stelle sich der Buchstabe im Alphabet befindet und gibt den Index zur�ck
		 * falls der Buchstabe nicht enthalten ist wird eine Exception geworfen
		 * @param letter Buchstabe, dessen Index gesucht ist
		 * @return Index des Buchstabens
		 */
		@SuppressWarnings("finally")
		public int getIndex(char letter){
			int result = -1;
			
			try{
				char[] tempAlph = this.asCharArray();
				boolean found = false;
				
				for(int i=0; i<tempAlph.length; i++){
					if (tempAlph[i] == letter) { 
						found = true;
						result = i;
						}  
					}
				if (found) return result; 
				else throw new ArrayIndexOutOfBoundsException();
			 } 
			catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Illegaler Array-Zugriff Buchstabe nicht enthalten");
			}
			
			finally{
				return result;
			}
		}
		
		/**
		 * Liefert den Buchstaben zum Index.
		 * @param index ein gegebener Index
		 * @return der zum Index passende Buchstabe
		 */
		public char getChar(int index) {
			return alphabet.get(index);
		}
		
		/**
		 * Gibt Gr��e des Alphabets zur�ck.
		 * @return Gr��e des Alphabets
		 */
		public int size() {
			return alphabet.size();
		}
		
		/**
		 * �berpr�ft, ob gegebener Character im Alphabet enthalten ist.
		 * @param chr zu pr�fender Buchstabe
		 * @return true, falls der Buchstabe enthalten ist, andernfalls false
		 */
		public boolean contains(char chr) {
			char[] tempAlph = this.asCharArray();	
			for (int i = 0; i < tempAlph.length; i++) {
						if (tempAlph[i] == chr) return true; 
					}
			
			return false; 
		}
		
		/**
		 * �berpr�ft, ob ein gegebenes Wort nur Buchstaben enth�lt, die im Alphabet enthalten
		 * sind.
		 * @param word ein zu pr�fendes Wort
		 * @return true, falls das Wort legal ist, andernfalls false
		 */
		public boolean allows(String word) {
			char[] wordArray = word.toCharArray();
			boolean allowed = true;
			if (word.equals("")) allowed = false;
			for (int i = 0; i < wordArray.length; i++) {
				if (contains(wordArray[i]) == false) allowed = false;
			}
			return allowed;
		}
		
		/**
		 * L�scht alle Buchstaben aus einem gegebenen String, die nicht Teil des Alphabets
		 * sind.
		 * @param ein gegebener String
		 */
		public String normalize(String input) {
			if (allows(input) == true) return input;
			else {
				char[] inputArray = input.toCharArray();
				StringBuilder result = new StringBuilder(inputArray.length);
				for (int i = 0; i < inputArray.length; i++) {
					if (contains(inputArray[i]) == true) result.append(inputArray[i]);
				}
				return result.toString();
			}
		}

		/**
		 * Erzeugt einen Iterator f�r das Alphabet.
		 * @return Iterator
		 */
		@Override
		public Iterator<Character> iterator() {
			Iterator<Character> i = alphabet.iterator();
			return i;
		}

		@Override
		public String toDisplay(String ciphertext) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<Integer, List<String>> ngramize(String text, int... lengths) {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * Dreht das Alphabet um x Stellen.
		 * @param alphabet das zu drehende Alphabet
		 * @param shift Zahl, um die das Alphabet verschoben werde soll
		 * @return das verschobene Alphabet
		 */
		@Override
		public de.tudarmstadt.gdi1.project.alphabet.Alphabet shiftAlphabet(
				de.tudarmstadt.gdi1.project.alphabet.Alphabet alphabet,
				int shift) {
			Alphabet dest = new Alphabet(); // neue Alphabet, in das verschl�sselt wird
			dest.clearAlphabet();
			
			for(int i = 0; i < alphabet.size(); i++){  //Iteriert durch das Alphabet und verschiebt es um den wert von key
				if(i+shift > alphabet.size()-1) // wenn Z�hler + Verschiebung gr��er als die L�nge des Alphabets ist 
					dest.addChar(alphabet.getChar(i+shift-alphabet.size())); //muss es wieder von vorne wetergehen.
				else dest.addChar(alphabet.getChar(i+shift)); //1. Buchstabe in dest = (1+key)ter Buchstabe in alphabet
			}
			return dest;
		}

		/**
		 * Dreht ein Alphabet um.
		 * @param das zu drehende Alphabet
		 * @return das umgedrehte Alphabet
		 */
		@Override
		public de.tudarmstadt.gdi1.project.alphabet.Alphabet reverseAlphabet(
				de.tudarmstadt.gdi1.project.alphabet.Alphabet alphabet) {
			char[] tempArray = alphabet.asCharArray();
			char[] reversedArray = new char[tempArray.length];
			ArrayList<Character> tempList = new ArrayList<>();
			
			for (int i = 1; i <= tempArray.length; i++) {
				reversedArray[i-1] = tempArray[tempArray.length-i];
				tempList.add(reversedArray[i-1]);
			}
			
			alphabet = new Alphabet(tempList);
			return alphabet;
			
		}

		@Override
		public boolean containsSameCharacters(
				de.tudarmstadt.gdi1.project.alphabet.Alphabet alphabet1,
				de.tudarmstadt.gdi1.project.alphabet.Alphabet alphabet2) {
			// TODO Auto-generated method stub
			return false;
		}

		/**
		 * Erzeugt ein zuf�llig angeordnetes Alphabet.
		 * @param alphabet Das zu randomisierende Alphabet
		 * @return das randomisierte Alphabet
		 */
		@Override
		public de.tudarmstadt.gdi1.project.alphabet.Alphabet randomizeAlphabet(
				de.tudarmstadt.gdi1.project.alphabet.Alphabet alphabet) {
			
			Random random = new Random(); //Zufallsgenerator
			char[] rAlphabet = new char[alphabet.asCharArray().length]; //neues Alphabet
			
			int[] indicesUsed = new int[alphabet.asCharArray().length]; //verbrauchte Indices
			int count = 0; //Counter
			Set<Character> tempSet = new HashSet<>(); //tempor�res Hilfsset
			
			while (count < alphabet.asCharArray().length) {
				int actualRandom = random.nextInt(26); //Zufallszahl von 0 bis 25
				boolean used = false; //besagt, ob ein Index schon gebraucht wurde
				for (int i = 0; i < count; i++) {
					if (indicesUsed[i] == actualRandom) used = true; //setzt used auf true,
					//falls der Index mit der Zufallszahl identisch ist
				}
				if (used == true) break; //bricht ab, wenn used true ist
				rAlphabet[count] = getChar(actualRandom); //setzt ansonsten gegenw�rtige
				//Stelle im neuen Alphabet auf den jeweiligen Zufallsbuchstaben
				indicesUsed[count] = actualRandom; //Zufallszahl wird den verbrauchten 
				//Indices hinzugef�gt
				tempSet.add(rAlphabet[count]); //dem tempor�ren Set wird der gegenw�rtige
				//Buchstabe des neuen Alphabets hinzugef�gt
				count++; //der Counter wird hochgez�hlt
			}
			
			alphabet = new Alphabet(tempSet); //das Alphabet wird mit dem neuen Alphabet
			//initialisiert
			return alphabet;
		}
		
		
		/**
		 * F�gt dem Alphabet einen Character hinzu, sofern m�glich.
		 * @param chr der hinzuzuf�gende Character
		 */
		public void addChar(char chr) {
			try { 
				if (!(alphabet.contains(chr))) alphabet.add(chr);
				else throw new InvalidCharacterException();
			}
			catch(InvalidCharacterException ic) {
				System.out.println("Illegal: Dieser Buchstabe ist bereits im Alphabet enthalten!");
			}
		}
		
		/**
		 * Entfernt einen Buchstaben aus dem Alphabet.
		 * @param chr zu entfernender Buchstabe
		 */
		public void removeChar(char chr) {
			alphabet.remove(chr);
		}
		
		/**
		 * Leer das Alphabet.
		 */
		public void clearAlphabet() {
			alphabet.clear();
		}
		
		/**
		 * Zeigt das Alphabet als ein String an.
		 * @return Alphabet als String
		 */
		@Override
		public String toString() {
			StringBuilder result = new StringBuilder(this.size()*2+1);
			Iterator<Character> it = alphabet.iterator();
			while (it.hasNext()) {
				result.append(it.next() + " ");
			}
			return result.toString();
		}
		
		
		
	
}
