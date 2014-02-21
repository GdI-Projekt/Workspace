package de.tudarmstadt.gdi1.project.eigeneTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.Alphabet;
import de.tudarmstadt.gdi1.project.CaesarChiffre;

public class CaesarChiffreTest {
	CaesarChiffre c1;
	CaesarChiffre c2;
	
	@Before
	public void init(){
		System.out.print("Test");
		Alphabet abc = new Alphabet();
		c1 = new CaesarChiffre(abc, 3);
		c2 = new CaesarChiffre(abc, 10);
		System.out.println(c1.getDest().toString());
		System.out.println(c2.getDest().toString());
	}

	@Test
	public void testEncrypt() {
		
		assertEquals("d", c1.encrypt("a"));
		assertEquals("jlwduuh", c1.encrypt("gitarre"));
		assertEquals("oscoxlkrx", c2.encrypt("eisenbahn"));
	}
	
	@Test
	public void testDecrypt(){
		assertEquals("fernseher", c1.decrypt("ihuqvhkhu"));
		assertEquals("caesar", c2.decrypt("mkockb"));
	}

}
