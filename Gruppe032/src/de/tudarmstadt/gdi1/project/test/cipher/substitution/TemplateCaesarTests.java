package de.tudarmstadt.gdi1.project.test.cipher.substitution;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.Caesar;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateCaesarTests {

	private Alphabet alph = TemplateTestUtils.getDefaultAlphabet();

	@Test
	public void testTemplateCaesarShiftZero() {
		Caesar c = TemplateTestCore.getFactory().getCaesarInstance(0, alph);
		assertEquals("hallowelt", c.encrypt("hallowelt"));
		assertEquals("hallowelt", c.decrypt("hallowelt"));
	}

	@Test
	public void testTemplateCaesarShiftOne() {
		Caesar c = TemplateTestCore.getFactory().getCaesarInstance(1, alph);
		assertEquals("bcd", c.encrypt("abc"));
		assertEquals("abc", c.encrypt("zab"));
		assertEquals("zab", c.decrypt("abc"));
		assertEquals("abc", c.decrypt("bcd"));
	}

	@Test
	public void testTemplateCaesarReflexivity() {
		for (int i = 0; i < 26; i++) {
			Caesar c = TemplateTestCore.getFactory().getCaesarInstance(i, alph);
			assertEquals("helloworld", c.decrypt(c.encrypt("helloworld")));
		}
	}
}
