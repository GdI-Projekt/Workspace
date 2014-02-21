package de.tudarmstadt.gdi1.project.test.analysis;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.vigenere.VigenereCryptanalysis;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateVigenereCryptanalysisTests {

	private VigenereCryptanalysis vi = TemplateTestCore.getFactory().getVigenereCryptanalysisInstance();

	@Test
	public void testTemplateVigenereCryptanalysisKnownCiphertextKeylength() {
		assertArrayEquals(new Integer[] { 1, 3, 9 },
				vi.knownCiphertextAttack("ifbohzxififbompknuhkhojcomfxfn").toArray());
	}

	@Test
	public void testTemplateVigenereCryptanalysisKnownPlaintextNorepeat() {
		assertEquals("schluesse",
				vi.knownPlaintextAttack("zcswiawdx", "hallowelt", TemplateTestUtils.getDefaultAlphabet()));
	}

	@Test
	public void testTemplateVigenereCryptanalysisKnownPlaintextRepeatHalf() {
		assertEquals("schluessel",
				vi.knownPlaintextAttack("zcswiawdxssnszqidl", "hallowelthallowelt", TemplateTestUtils.getDefaultAlphabet()));
	}

	@Test
	public void testTemplateVigenereCryptanalysisKnownPlaintextRepeatTwice() {
		assertEquals(
				"schluessel",
				vi.knownPlaintextAttack("zcswiawdxssnszqidllldnvhypl", "hallowelthallowelthallowelt",
						TemplateTestUtils.getDefaultAlphabet()));
	}

	@Test
	public void testTemplateVigenereCryptanalysisKnownCiphertextCribs() {
		Distribution dist = TemplateTestCore.getFactory().getDistributionInstance(
				TemplateTestUtils.getMinimalAlphabet(), "aaaaaaaaabbbbbbccc", 3);
		assertEquals("abc",
				vi.knownCiphertextAttack("abcabcabcbcdbcdcda", dist, Arrays.asList("aaaaaaaaa", "bbbbbb", "ccc")));
	}

}
