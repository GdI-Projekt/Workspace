package de.tudarmstadt.gdi1.project.test.cipher.substitution;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.Factory;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.MonoalphabeticCipher;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateMonoalphabeticCipherTests {
	private final Factory f = TemplateTestCore.getFactory();

	@Test
	public void testTemplateMonoalphabeticCipherIdentity() {
		MonoalphabeticCipher c = f.getMonoalphabeticCipherInstance(TemplateTestUtils.getDefaultAlphabet(),
				TemplateTestUtils.getDefaultAlphabet());
		assertEquals("hallowelt", c.encrypt("hallowelt"));
		assertEquals("hallowelt", c.decrypt("hallowelt"));
	}

	@Test
	public void testTemplateMonoalphabeticCipherSmallAlphabet() {
		MonoalphabeticCipher c = f.getMonoalphabeticCipherInstance(f.getAlphabetInstance(Arrays.asList('a', 'b', 'c')),
				f.getAlphabetInstance(Arrays.asList('x', 'y', 'z')));
		assertEquals("xyz", c.encrypt("abc"));
		assertEquals("xxx", c.encrypt("aaa"));
		assertEquals("cba", c.decrypt("zyx"));
		assertEquals("bbb", c.decrypt("yyy"));
	}

	@Test
	public void testTemplateMonoalphabeticCipherReflexivity() {
		MonoalphabeticCipher c = f.getMonoalphabeticCipherInstance(TemplateTestUtils.getDefaultAlphabet(),
				f.getAlphabetInstance(Arrays.asList('s', 'b', 'i', 'u', 'r', 'f', 't', 'h', 'c', 'q', 'l', 'k', 'w', 'p', 'o', 'n', 'j',
						'e', 'a', 'g', 'd', 'v', 'm', 'x', 'z', 'y')));
		assertEquals("helloworld", c.decrypt(c.encrypt("helloworld")));
	}
}
