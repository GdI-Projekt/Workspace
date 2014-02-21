package de.tudarmstadt.gdi1.project;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;

/**
 * 
 * @author Laura, Janik
 *
 */
public class CaesarAnalysis implements de.tudarmstadt.gdi1.project.analysis.caeser.CaesarCryptanalysis  {

	Integer key;
	
	@Override
	public Integer knownCiphertextAttack(String ciphertext,
			Distribution distribution) {
				//häufigste Buchstaben in der gegebenen Distribution
				char mostFrequently = distribution.getSorted(1).get(0).toCharArray()[0];
				char secFrequently = distribution.getSorted(1).get(1).toCharArray()[0];
				char thirdFrequently = distribution.getSorted(1).get(2).toCharArray()[0];
				
				Alphabet alph = (Alphabet) distribution.getAlphabet(); //aktuelles Alphabet
				//Distribution zur Bestimmung der häufigsten Buchstaben im Ciphertext
				Distribution cipherDis = new DistributionC(alph, ciphertext, 0); 
				
				//Ermittlung der häufigsten Buchstaben im Ciphertext
				char mostFrequentlyInCipher = cipherDis.getSorted(1).get(1).toCharArray()[0];
				char secFrequentlyInCipher = cipherDis.getSorted(1).get(2).toCharArray()[0];
				char thirdFrequentlyInCipher = cipherDis.getSorted(1).get(3).toCharArray()[0];
				
				//Ermittlung des Schlüssels nach am häufigsten vorkommenden Buchstaben
				int key1;
				int key2;
				int key3;
				
				if (alph.getIndex(mostFrequently) >= alph.getIndex(mostFrequentlyInCipher)) 
					key1 = alph.getIndex(mostFrequently) - alph.getIndex(mostFrequentlyInCipher);
				else key1 = alph.getIndex(mostFrequentlyInCipher) - alph.getIndex(mostFrequently);
				
				if (alph.getIndex(secFrequently) >= alph.getIndex(secFrequentlyInCipher)) 
					key2 = alph.getIndex(secFrequently) - alph.getIndex(secFrequentlyInCipher);
				else key2 = alph.getIndex(secFrequentlyInCipher) - alph.getIndex(secFrequently);
				
				//Wenn der durch die am häufigsten vorkommenden Buchstaben ermittelte Schlüssel mit dem 
				//der am zweithäufigsten Buchstaben ermittelten Schlüssel übereinstimmt, ist dieser 
				//mit großer Wahrscheinlichkeit der richtige Schlüssel, womit dieser als key festgelegt
				//wird.
				//Stimmt dieser nicht damit überein, wird der durch die am dritthäufigsten vorkommenden
				//Buchstaben ermittelte Schlüssel zum Vergleich gezogen. Stimmt dieser mit key1 überein,
				//dann ist dieser wahrscheinlich der richtige Schlüssel; stimmt der mit key2 überein, so
				//ist es wahrscheinlich dieser.
				//Sind alle drei ermittelten keys unterschiedlich, so ist es dennoch am
				//wahrscheinlich, dass key1 der gesuchte Schlüssel ist, womit dieser als key festgelegt
				//wird.
				if (key1 == key2) key = key1;
				else {
					if (alph.getIndex(thirdFrequently) >= alph.getIndex(thirdFrequentlyInCipher)) 
						key3 = alph.getIndex(thirdFrequently) - alph.getIndex(thirdFrequentlyInCipher);
					else key3 = alph.getIndex(thirdFrequentlyInCipher) - alph.getIndex(thirdFrequently);
					
					if (key1 == key3) key = key1;
					else if (key2 == key3) key = key2;
					else key = key1;
				}
				
				return key;
				
			}

	@Override
	public Integer knownCiphertextAttack(String ciphertext,
			Dictionary dictionary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer knownCiphertextAttack(String ciphertext,
			Distribution distribution, Dictionary dict) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer knownPlaintextAnalysis(String cipher, String plain, Alphabet alph){
		int countercipher=0;
		int counterplain=0;
		char charcipher = cipher.charAt(0);
		char charplain = cipher.charAt(0);
		while( charcipher!= alph.getChar(countercipher)){
			countercipher++;
		}
		while( charplain!= alph.getChar(counterplain)){
			counterplain++;
		}
		int caesar = countercipher-counterplain+26%26;
		return caesar;
	}

	@Override
	public
	Integer knownPlaintextAttack(String ciphertext, String plaintext,
			de.tudarmstadt.gdi1.project.alphabet.Alphabet alphabet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public
	Integer knownPlaintextAttack(String ciphertext, String plaintext,
			Distribution distribution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public
	Integer knownPlaintextAttack(String ciphertext, String plaintext,
			Distribution distribution, Dictionary dictionary) {
		// TODO Auto-generated method stub
		return null;
	}



}
