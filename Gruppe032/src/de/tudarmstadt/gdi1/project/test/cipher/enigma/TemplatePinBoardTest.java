package de.tudarmstadt.gdi1.project.test.cipher.enigma;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.Factory;
import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.cipher.enigma.PinBoard;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplatePinBoardTest {
	private final Factory f = TemplateTestCore.getFactory();

	@Test
	public void testTranslate() {
		Alphabet a = TemplateTestUtils.getDefaultAlphabet();
		Alphabet b = TemplateTestUtils.getReversedDefaultAlphabet();
		Alphabet c = TemplateTestUtils.getMinimalAlphabet();
		Alphabet d = f.getAlphabetInstance(Arrays.asList('a', 'c', 'b', 'd'));
		PinBoard pb1 = f.getPinBoardInstance(a, b);
		PinBoard pb2 = f.getPinBoardInstance(c, d);

		for (int i = 0; i < a.size(); i++) {
			assertEquals(b.getChar(i), pb1.translate(a.getChar(i)));
		}
		assertEquals('a', pb2.translate('a'));
		assertEquals('c', pb2.translate('b'));
		assertEquals('b', pb2.translate('c'));
		assertEquals('d', pb2.translate('d'));
	}
}
