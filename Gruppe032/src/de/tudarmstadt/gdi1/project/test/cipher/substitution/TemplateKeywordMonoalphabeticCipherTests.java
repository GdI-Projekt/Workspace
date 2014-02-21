package de.tudarmstadt.gdi1.project.test.cipher.substitution;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.Factory;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.KeywordMonoalphabeticCipher;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateKeywordMonoalphabeticCipherTests {
	private final Factory f = TemplateTestCore.getFactory();

	@Test
	public void testTemplateKeywordMonoalphabeticCipherZeroLetters() {
		KeywordMonoalphabeticCipher c = f.getKeywordMonoalphabeticCipherInstance("", TemplateTestUtils.getDefaultAlphabet());
		assertEquals("szooldvog", c.encrypt("hallowelt"));
		assertEquals("szooldvog", c.decrypt("hallowelt"));
	}

	@Test
	public void testTemplateKeywordMonoalphabeticCipherOneLetter() {
		KeywordMonoalphabeticCipher c = f.getKeywordMonoalphabeticCipherInstance("x", TemplateTestUtils.getDefaultAlphabet());
		assertEquals("xzy", c.encrypt("abc"));
		assertEquals("axz", c.encrypt("zab"));
		assertEquals("zyx", c.decrypt("abc"));
		assertEquals("azy", c.decrypt("xab"));
	}

	@Test
	public void testTemplateKeywordMonoalphabeticCipherReflexivity() {
		KeywordMonoalphabeticCipher c = f.getKeywordMonoalphabeticCipherInstance("abcdef", TemplateTestUtils.getDefaultAlphabet());
		assertEquals("helloworld", c.decrypt(c.encrypt("helloworld")));
	}
}
