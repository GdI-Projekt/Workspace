package de.tudarmstadt.gdi1.project.eigeneTests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.Alphabet;
import de.tudarmstadt.gdi1.project.Polyalphabetic;



public class PolyalphabeticTest {

	Alphabet source = new Alphabet(Arrays.asList('a', 'b', 'c', 'd', 'e'));
	
	Alphabet dest1 = new Alphabet(Arrays.asList('w', 'q', 'r', 'z', 'e'));
	Alphabet dest2 = new Alphabet(Arrays.asList('1', '2', '3', '4', '5'));
	Alphabet dest3 = new Alphabet(Arrays.asList('q', 'w', 'e', 'r', 't'));
	Alphabet[] dest = {dest1, dest2, dest3};

	Polyalphabetic p = new Polyalphabetic(source, dest);
	
	@Test
	public void testTranslate() {
		assertEquals('t', p.translate('e', 14));
		assertEquals('w', p.translate('a', 3));
		assertEquals('3', p.translate('c', 7));
	}

	@Test
	public void testReverseTranslate(){
		assertEquals('e', p.reverseTranslate('5', 7));
		assertEquals('b', p.reverseTranslate('q', 3));
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testInvalidTranslate(){
		p.translate('k', 4);
	}
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testInvalidReverseTranslate(){
		assertEquals('x', p.reverseTranslate('5', 6));
	}
	
	@Test
	public void testEncrypt(){
		assertEquals("q1re", p.encrypt("bade"));
		assertEquals("e2ww3w", p.encrypt("ebbacb"));
	}
	
	@Test
	public void testDecrypt(){
		assertEquals("aaeebcd", p.decrypt("w1te2ez"));
		assertEquals("cebdca", p.decrypt("r5wz3q"));
		
	}
}
