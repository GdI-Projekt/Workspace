package de.tudarmstadt.gdi1.project.eigeneTests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;
import org.junit.Before;

import de.tudarmstadt.gdi1.project.Alphabet;
import de.tudarmstadt.gdi1.project.Monoalphabetic;

public class MonoalphabeticTest {
	Alphabet source;
	Alphabet key;
	char[] alph1 = {'a', 'b', 'c', 'd', 'e'};
	char[] alph2 = {'1', '2', '3', '4', '5'};
	LinkedList<Character> alphList1 = new LinkedList<Character>();
	LinkedList<Character> alphList2 = new LinkedList<Character>();
	Monoalphabetic m;
	
	@Before
	public void init(){
		for(int i=0; i < alph1.length; i++){
			alphList1.add(alph1[i]);
			alphList2.add(alph2[i]);
		}
		source = new Alphabet(alphList1);
		key = new Alphabet(alphList2);
		
		m = new Monoalphabetic(key, source);
	}
	@Test
	public void testTranslate() {
		assertEquals('2', m.translate('b', 5));
		assertEquals('2', m.translate('b', 3649));
		assertEquals('4', m.translate('d', 1));
	}
	
	@Test
	public void testReverseTranslate(){
		assertEquals('a', m.reverseTranslate('1', 48));
		assertEquals('e', m.reverseTranslate('5', 23));
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testInvalidTranslate(){
		m.reverseTranslate('z', 2);
	}
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testInvalidReverseTranslate(){
		m.reverseTranslate('a', 4);
	}
	@Test 
	public void testEncrypt(){
		assertEquals("2412", m.encrypt("bdab"));
		assertEquals("5142", m.encrypt("eadb"));
	}
	@Test
	public void testDecrypt(){
		assertEquals("eaba",m.decrypt("5121"));
		assertEquals("ecdab", m.decrypt("53412"));
	}
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestInvalidEncrypt(){
		m.encrypt("hallo");
	}
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void TestInvalidDecrypt(){
		m.decrypt("abcd");
	}
}
