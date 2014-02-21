package de.tudarmstadt.gdi1.project.test.cipher.enigma;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.Factory;
import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.cipher.enigma.Rotor;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateRotorTest {

	private Rotor r;
	private final Factory f = TemplateTestCore.getFactory();

	@Test
	public void stupidRotorTest() {
		Alphabet testAlph = TemplateTestUtils.getDefaultAlphabet();
		r = f.getRotorInstance(testAlph, testAlph, 0);
		for (int i = 0; i < testAlph.size(); i++) {
			char curChar = testAlph.getChar(i);
			Assert.assertEquals(curChar, r.translate(curChar, true));
			Assert.assertEquals(curChar, r.translate(curChar, false));
		}
	}

	@Test
	public void TestTranslate() {
		Alphabet outputAlphabet = f.getAlphabetInstance(Arrays.asList('b', 'c', 'a', 'd'));
		r = f.getRotorInstance(TemplateTestUtils.getMinimalAlphabet(), outputAlphabet, 0);
		Assert.assertEquals('b', r.translate('a', true));
		Assert.assertEquals('c', r.translate('b', true));
		Assert.assertEquals('a', r.translate('c', true));
		Assert.assertEquals('d', r.translate('d', true));
		Assert.assertEquals('c', r.translate('a', false));
		Assert.assertEquals('a', r.translate('b', false));
		Assert.assertEquals('b', r.translate('c', false));
		Assert.assertEquals('d', r.translate('d', false));
	}

	@Test
	public void TestRotate() {
		Alphabet outputAlphabet = f.getAlphabetInstance(Arrays.asList('b', 'c', 'a', 'd'));
		r = f.getRotorInstance(TemplateTestUtils.getMinimalAlphabet(), outputAlphabet, 0);
		Assert.assertEquals('b', r.translate('a', true));
		Assert.assertEquals('c', r.translate('a', false));
		r.rotate();
		Assert.assertEquals('b', r.translate('a', true));
		Assert.assertEquals('d', r.translate('a', false));
	}

	@Test
	public void TestReset() {
		Alphabet outputAlphabet = f.getAlphabetInstance(Arrays.asList('b', 'c', 'a', 'd'));
		r = f.getRotorInstance(TemplateTestUtils.getMinimalAlphabet(), outputAlphabet, 2);
		Assert.assertEquals('c', r.translate('a', true));
		r.rotate();
		r.reset();
		Assert.assertEquals('c', r.translate('a', true));
	}
}
