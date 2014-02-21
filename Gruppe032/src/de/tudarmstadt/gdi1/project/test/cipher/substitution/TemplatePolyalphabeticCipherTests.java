package de.tudarmstadt.gdi1.project.test.cipher.substitution;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.Factory;
import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.cipher.substitution.polyalphabetic.PolyalphabeticCipher;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplatePolyalphabeticCipherTests {

	private final Factory f = TemplateTestCore.getFactory();

	@Test
	public void testTemplatePolyalphabeticCipherSingleIdentity() {
		PolyalphabeticCipher c = f.getPolyalphabeticCipherInstance(TemplateTestUtils.getDefaultAlphabet(),
				new Alphabet[] { TemplateTestUtils.getDefaultAlphabet() });
		assertEquals("hallowelt", c.encrypt("hallowelt"));
		assertEquals("hallowelt", c.decrypt("hallowelt"));
	}

	@Test
	public void testTemplatePolyalphabeticCipherSmallOneKey() {
		PolyalphabeticCipher c = f.getPolyalphabeticCipherInstance(f.getAlphabetInstance(Arrays.asList('a', 'b', 'c')),
				new Alphabet[] { f.getAlphabetInstance(Arrays.asList('x', 'y', 'z')) });
		assertEquals("xyz", c.encrypt("abc"));
		assertEquals("xxx", c.encrypt("aaa"));
		assertEquals("cba", c.decrypt("zyx"));
		assertEquals("bbb", c.decrypt("yyy"));
	}

	@Test
	public void testTemplatePolyalphabeticCipherSmallTwoKeys() {
		PolyalphabeticCipher c = f
				.getPolyalphabeticCipherInstance(
						f.getAlphabetInstance(Arrays.asList('a', 'b', 'c')),
						new Alphabet[] { f.getAlphabetInstance(Arrays.asList('u', 'v', 'w')),
								f.getAlphabetInstance(Arrays.asList('x', 'y', 'z')) });
		assertEquals("uywxvz", c.encrypt("abcabc"));
		assertEquals("uxux", c.encrypt("aaaa"));
		assertEquals("ccbbaa", c.decrypt("wzvyux"));
		assertEquals("bbbb", c.decrypt("vyvy"));
	}

	@Test
	public void testTemplatePolyalphabeticCipherReflexivity() {
		PolyalphabeticCipher c = f
				.getPolyalphabeticCipherInstance(TemplateTestUtils.getDefaultAlphabet(),
						new Alphabet[] { f.getAlphabetInstance(Arrays.asList('s',
								'b', 'i', 'u', 'r', 'f', 't', 'h', 'c', 'q', 'l', 'k', 'w',
								'p', 'o', 'n', 'j', 'e', 'a', 'g', 'd', 'v', 'm', 'x', 'z', 'y')) });
		assertEquals("helloworld", c.decrypt(c.encrypt("helloworld")));
	}
}
