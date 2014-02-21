package de.tudarmstadt.gdi1.project.test.cipher.substitution;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.Factory;
import de.tudarmstadt.gdi1.project.cipher.substitution.polyalphabetic.Vigenere;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateVigenereCipherTests {
	private final Factory f = TemplateTestCore.getFactory();

	@Test
	public void testTemplateVigenereCipherIdentity() {
		Vigenere c = f.getVigenereCipherInstance("a", TemplateTestUtils.getDefaultAlphabet());
		assertEquals("hallowelt", c.encrypt("hallowelt"));
		assertEquals("hallowelt", c.decrypt("hallowelt"));
	}

	@Test
	public void testTemplateVigenereCipherOneLetter() {
		Vigenere c = f.getVigenereCipherInstance("b", f.getAlphabetInstance(Arrays.asList('a', 'b', 'c')));
		assertEquals("bca", c.encrypt("abc"));
		assertEquals("bbb", c.encrypt("aaa"));
		assertEquals("bac", c.decrypt("cba"));
		assertEquals("aaa", c.decrypt("bbb"));
	}

	@Test
	public void testTemplateVigenereCipherThreeLetters() {
		Vigenere c = f.getVigenereCipherInstance("bca", f.getAlphabetInstance(Arrays.asList('a', 'b', 'c')));
		assertEquals("bacbac", c.encrypt("abcabc"));
		assertEquals("bcab", c.encrypt("aaaa"));
		assertEquals("ccbbaa", c.decrypt("abbcca"));
		assertEquals("bbbb", c.decrypt("cabc"));
	}

	@Test
	public void testTemplateVigenereCipherReflexivity() {
		Vigenere c = f.getVigenereCipherInstance("abcdefghijklmnopqrstuvxyz", TemplateTestUtils.getDefaultAlphabet());
		assertEquals("helloworld", c.decrypt(c.encrypt("helloworld")));
	}

}