package de.tudarmstadt.gdi1.project.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;
import de.tudarmstadt.gdi1.project.utils.Utils;

public class TemplateUtilsTests {

	private Utils utils;

	@Before
	public void init() {
		this.utils = TemplateTestCore.getFactory().getUtilsInstance();
	}

	@Test
	public void testTemplateUtilsToDisplay() {
		String ugly = "abcdefghijklmnopqrstufwxyz";
		String pretty = "abcdefghij klmnopqrst ufwxyz";
		assertEquals(pretty, utils.toDisplay(ugly));
		String ugly2 = "abcdefghijklmnopqrstufwxyz1234abcdefghijklmnopqrstufwxyz1234abcdefghijklmnopqrstufwxyz1234abcdefghijklmnopqrstufwxyz123";
		String pretty2 = "abcdefghij klmnopqrst ufwxyz1234 abcdefghij klmnopqrst ufwxyz1234" + System.lineSeparator()
				+ "abcdefghij klmnopqrst ufwxyz1234 abcdefghij klmnopqrst ufwxyz123";
		assertEquals(pretty2, utils.toDisplay(ugly2));
	}

	@Test
	public void testTemplateUtilsNgramize() {
		String text = "abcde";
		Map<Integer, List<String>> ngrams = utils.ngramize(text, 1, 3, 4, 5);
		List<String> letters = ngrams.get(1);
		assertEquals(5, letters.size());
		assertEquals("a", letters.get(0));
		assertEquals("b", letters.get(1));
		assertEquals("c", letters.get(2));
		assertEquals("d", letters.get(3));
		assertEquals("e", letters.get(4));
		assertEquals(null, ngrams.get(2));
		List<String> trigrams = ngrams.get(3);
		assertEquals(3, trigrams.size());
		assertEquals("abc", trigrams.get(0));
		assertEquals("bcd", trigrams.get(1));
		assertEquals("cde", trigrams.get(2));
		List<String> quadgrams = ngrams.get(4);
		assertEquals("abcd", quadgrams.get(0));
		assertEquals("bcde", quadgrams.get(1));
		assertEquals("abcde", ngrams.get(5).get(0));
		assertEquals(null, ngrams.get(6));

	}

	@Test
	public void testTemplateUtilsShiftAlphabet() {
		Alphabet a = TemplateTestUtils.getMinimalAlphabet();
		Alphabet b = utils.shiftAlphabet(a, 1);
		Alphabet c = utils.shiftAlphabet(a, 2);
		Alphabet d = utils.shiftAlphabet(a, 4);
		Alphabet e = utils.shiftAlphabet(a, -1);
		assertEquals('b', b.getChar(0));
		assertEquals('c', b.getChar(1));
		assertEquals('d', b.getChar(2));
		assertEquals('a', b.getChar(3));

		assertEquals('c', c.getChar(0));
		assertEquals('b', c.getChar(3));

		assertEquals('a', d.getChar(0));

		assertEquals('d', e.getChar(0));
	}

	@Test
	public void testTemplateUtilsReverseAlphabet() {
		Alphabet alphabet = TemplateTestUtils.getMinimalAlphabet();
		org.junit.Assert.assertArrayEquals(TemplateTestUtils.getReversedMinimalAlphabet().asCharArray(), utils.reverseAlphabet(alphabet)
				.asCharArray());
	}

	@Test
	public void testTemplateUtilsRandomizedAlphabet() {
		Alphabet alphabet = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = utils.randomizeAlphabet(alphabet);

		assertEquals(alphabet.size(), target.size());

		for (char c : alphabet)
			assertTrue(target.contains(c));
	}
}
