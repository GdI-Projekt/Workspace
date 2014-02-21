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
	 * Geht jeden Buchstaben des Textes durch und übersetzt diesen und gibt den verschlüsselten
	 * Text zurück
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
	 * Geht jeden Buchstaben des Textes durch und übersetzt diesen und gibt den entschlüsselten
	 * Text zurück
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
