package de.tudarmstadt.gdi1.project.test.analysis;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.caeser.CaesarCryptanalysis;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateCaesarCryptanalysisTests {

	private Distribution dist;
	private CaesarCryptanalysis ca;

	@Before
	public void init() {
		dist = TemplateTestCore.getFactory().getDistributionInstance(TemplateTestUtils.getMinimalAlphabet(),
				"aaaabbbccd", 3);

		ca = TemplateTestCore.getFactory().getCaesarCryptanalysisInstance();
	}

	@Test
	public void testTemplateCaesarCryptanalysisKnownCiphertextDistributionOneFrequency() {
		assertEquals(0, (int) ca.knownCiphertextAttack("aaabcd", dist));
		assertEquals(1, (int) ca.knownCiphertextAttack("abbbcd", dist));
		assertEquals(2, (int) ca.knownCiphertextAttack("abcccd", dist));
		assertEquals(3, (int) ca.knownCiphertextAttack("abcddd", dist));
	}
}
