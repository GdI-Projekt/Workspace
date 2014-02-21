package de.tudarmstadt.gdi1.project.test.alphabet;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateDictionaryTests {

	@Test
	public void testTemplateDictionaryString() {
		Dictionary d = TemplateTestCore.getFactory().getDictionaryInstance(
				TemplateTestUtils.getDefaultAlphabet(),
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vel mi pulvinar, " +
						"euismod lorem eget, viverra enim. Cras quis pharetra. "
				);

		Assert.assertTrue(d.contains("vel"));
		Assert.assertFalse(d.contains("Lorem"));
	}

	@Test
	public void testTemplateDictionarySize() {
		Dictionary d = TemplateTestCore.getFactory().getDictionaryInstance(
				TemplateTestUtils.getDefaultAlphabet(), "test hallo welt");
		Assert.assertEquals(3, d.size());
	}

	@Test
	public void testTemplateDictionaryGet() {
		Dictionary d = TemplateTestCore.getFactory().getDictionaryInstance(
				TemplateTestUtils.getDefaultAlphabet(), "hallo welt himmel");
		Assert.assertEquals("hallo", d.get(0));
		Assert.assertEquals("welt", d.get(2));
		Assert.assertEquals("himmel", d.get(1));
	}

	@Test
	public void testDictionaryIsNormalized() {
		Alphabet alphabet = TemplateTestUtils.getDefaultAlphabet();

		/* build new alphabet with space */
		List<Character> chars = new ArrayList<>();
		for (char c : alphabet.asCharArray())
			chars.add(c);
		chars.add(' ');
		chars.add(',');
		chars.add('!');
		chars.add('?');
		chars.add('.');
		Alphabet alphabetWithSpace = TemplateTestCore.getFactory().getAlphabetInstance(chars);

		Dictionary dic1 = TemplateTestCore.getFactory().getDictionaryInstance(alphabet, TemplateTestUtils.ALICE_PLAIN);
		Dictionary dic2 = TemplateTestCore.getFactory().getDictionaryInstance(alphabet,
				alphabetWithSpace.normalize("äöü " + TemplateTestUtils.ALICE_PLAIN));

		Assert.assertEquals(dic1.size(), dic2.size());

		for (int i = 0; i < dic1.size(); i++)
			Assert.assertEquals(dic1.get(i), dic2.get(i));
	}

	@Test
	public void testUnallowedWord() {
		Alphabet alphabet = TemplateTestUtils.getDefaultAlphabet();

		Dictionary dic1 = TemplateTestCore.getFactory().getDictionaryInstance(alphabet, "this word; is not allowed");
		Assert.assertTrue(dic1.contains("this"));
		Assert.assertFalse(dic1.contains("word"));
	}

}
