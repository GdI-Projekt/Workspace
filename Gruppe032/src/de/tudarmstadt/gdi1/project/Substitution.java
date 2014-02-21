package de.tudarmstadt.gdi1.project;

import de.tudarmstadt.gdi1.project.cipher.substitution.SubstitutionCipher;

/**
 * 
 * @author Stephanie, Thorsten
 *
 */
public abstract class Substitution implements SubstitutionCipher{

	/**
	 * encrypt
	 * Geht jeden Buchstaben des Textes durch und �bersetzt diesen und gibt den verschl�sselten
	 * Text zur�ck
	 */
	@Override
	public String encrypt(String text){
		String encrypted = "";
		
		for (int i=0; i<text.length(); i++){
			char l = translate(text.charAt(i), i);
			encrypted = encrypted + l;
		}
		return encrypted;
	}
	
	/**
	 * decrypt
	 * Geht jeden Buchstaben des Textes durch und �bersetzt diesen und gibt den entschl�sselten
	 * Text zur�ck
	 */
	@Override
	public String decrypt(String text){
		String decrypted = "";
		
		for(int i=0; i<text.length(); i++){
			char l = reverseTranslate(text.charAt(i), i);
			decrypted = decrypted +  l;
		}
		return decrypted;
		
	}
}
