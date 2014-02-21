package de.tudarmstadt.gdi1.project.test.cipher.enigma;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.Factory;
import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.cipher.enigma.ReverseRotor;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateReverseRotorTest {

	private final Factory f = TemplateTestCore.getFactory();

	@Test
	public void testTranslate() {
		Alphabet a = TemplateTestUtils.getDefaultAlphabet();
		Alphabet b = TemplateTestUtils.getReversedDefaultAlphabet();
		Alphabet c = TemplateTestUtils.getMinimalAlphabet();
		Alphabet d = f.getAlphabetInstance(Arrays.asList('d', 'c', 'b', 'a'));
		ReverseRotor rr1 = f.getReverseRotatorInstance(a, b);
		ReverseRotor rr2 = f.getReverseRotatorInstance(c, d);

		for (int i = 0; i < a.size(); i++) {
			assertEquals(b.getChar(i), rr1.translate(a.getChar(i)));
		}
		assertEquals('d', rr2.translate('a'));
		assertEquals('c', rr2.translate('b'));
		assertEquals('b', rr2.translate('c'));
		assertEquals('a', rr2.translate('d'));
	}
}
