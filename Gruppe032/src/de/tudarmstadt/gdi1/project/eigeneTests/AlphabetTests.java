package de.tudarmstadt.gdi1.project.eigeneTests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.Alphabet;
import de.tudarmstadt.gdi1.project.exception.InvalidCharacterException;

public class AlphabetTests {
	Alphabet alphabet1;
	Alphabet alphabet2;
	char[] alph1 = {'h', 'a', 'l', 'o', '!'};
	char[] alph2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	@Before
	public void init(){ 
		LinkedList<Character> alph1List = new LinkedList<Character>();
		for (int i=0; i<alph1.length; i++){
			alph1List.add(alph1[i]);
		}
		alphabet1 = new Alphabet(alph1List);
		alphabet2 = new Alphabet();
		}
	
	
	/**
	 * Testet ob wenn ein Alphabet mit 2 gleichen Buchstaben erstellt wird
	 * eine Exception geworfen wird
	 */
	
	@Test(expected = Exception.class) 
	public void testAlphabetWithSameEntry(){
		@SuppressWarnings("unused")
		Alphabet invalid1 = new Alphabet(Arrays.asList('a', 'b','a'));
		@SuppressWarnings("unused")
		Alphabet invalid2 = new Alphabet(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'b', 'l'));
	}
	
	/**
	 * Testet asCharArray
	 */
	@Test
	public void testAsCharArray(){
		assertArrayEquals(alph2, alphabet2.asCharArray());
		assertArrayEquals(alph1, alphabet1.asCharArray());
	}
	
	/**
	 * Testet checkAlphabet
	 */
	@Test
	public void testGetChar(){
		assertEquals('z', alphabet2.getChar(25));
		assertEquals('k', alphabet2.getChar(10));
		assertEquals('!', alphabet1.getChar(4));
		assertEquals('a', alphabet1.getChar(1));
	}

	@Test
	public void testSize(){
		assertEquals(26, alphabet2.size());
		assertEquals(5, alphabet1.size());
	}
	
	@Test 
	public void testGetIndex(){
		assertEquals(5, alphabet2.getIndex('f'));
		assertEquals(0, alphabet1.getIndex('h'));
	}
	
	
	@Test (expected = InvalidCharacterException.class)
	public void testAddInvalidChar(){
		alphabet2.addChar('a');
		alphabet1.addChar('!');
	}
	@Test
	public void testAddChar(){
		alphabet2.addChar('1');
		alphabet1.addChar('b');
		
		assertEquals(27, alphabet2.size());
		assertEquals(6, alphabet1.size());
	}
	
	@Test
	public void testContains(){
		assertEquals(true, alphabet1.contains('!'));
		assertEquals(false, alphabet1.contains('z'));
		assertEquals(true, alphabet2.contains('k'));
		assertEquals(false, alphabet2.contains('?'));
	}
	
	@Test
	public void testAllows(){
		assertEquals(true, alphabet1.allows("hallo!"));
		assertEquals(false, alphabet1.allows("taschentuch"));
		assertEquals(true, alphabet2.allows("strassenbahn"));
		assertEquals(false, alphabet2.allows("hallo!"));
	}
	
	@Test
	public void testNormalize(){
		assertEquals("ahh", alphabet1.normalize("taschentuch"));
		assertEquals("hallo!", alphabet1.normalize("hallo!"));
		assertEquals("straenbahn", alphabet2.normalize("straßenbahn"));
		assertEquals("hallo", alphabet2.normalize("hallo!"));
	}
	
	@Test
	public void testShiftAlphabet(){
		Alphabet shifted1 = (Alphabet) alphabet1.shiftAlphabet(alphabet1, 2);
		Alphabet shifted2 = (Alphabet) alphabet2.shiftAlphabet(alphabet2, 3);
		
		assertEquals('l', shifted1.getChar(0)); // aus h wird l
		assertEquals('o', shifted1.getChar(1));
		assertEquals('!', shifted1.getChar(2));
		assertEquals('h', shifted1.getChar(3));
		assertEquals('a', shifted1.getChar(4));
		
		assertEquals('e', shifted2.getChar(1)); // aus b wird e
		assertEquals('b', shifted2.getChar(24)); // aus y wird b
	}
	
	@Test
	public void testReverseAlphabet(){
		Alphabet reversed1 = (Alphabet) alphabet1.reverseAlphabet(alphabet1);
		Alphabet reversed2 = (Alphabet) alphabet2.reverseAlphabet(alphabet2);
		
		assertEquals('!', reversed1.getChar(0));
		assertEquals('o', reversed1.getChar(1));
		assertEquals('l', reversed1.getChar(2));
		assertEquals('a', reversed1.getChar(3));
		assertEquals('h', reversed1.getChar(4));
		
		assertEquals('x', reversed2.getChar(2));
		assertEquals('c', reversed2.getChar(23));
	}
	
	@Test
	public void testToString() {
		assertEquals("h a l o ! ", alphabet1.toString());
		assertEquals("a b c d e f g h i j k l m n o p q r s t u v w x y z ", alphabet2.toString());
	}

}
