package de.tudarmstadt.gdi1.project.eigeneTests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.Alphabet;
import de.tudarmstadt.gdi1.project.VigenereChiffre;

public class VigenereChiffreTest {
	Alphabet abc = new Alphabet();
	String key1 = "hallo";
	String key2 = "abc";
	VigenereChiffre v1 = new VigenereChiffre(key1, abc);
	VigenereChiffre v2 = new VigenereChiffre(key2, abc);

	@Test
	public void testEncrypt() {
		assertEquals("aadnvlnwlawe", v1.encrypt("taschenlampe"));
		assertEquals("wvpdftkftzf", v2.encrypt("wunderkerze"));
	}
	
	public void testDecrypt(){
		assertEquals("bettdecke", v1.decrypt("ieeerlcvp"));
		assertEquals("kamelritt", v2.encrypt("kboemtiuv"));
	}
}
