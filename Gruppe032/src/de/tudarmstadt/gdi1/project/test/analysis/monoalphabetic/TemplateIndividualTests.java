package de.tudarmstadt.gdi1.project.test.analysis.monoalphabetic;

import org.junit.Assert;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.Individual;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateIndividualTests {

	@Test
	public void testIndividual() {
		Alphabet alphabet = TemplateTestUtils.getDefaultAlphabet();
		Individual individualInstance = TemplateTestCore.getFactory().getIndividualInstance(alphabet, 25);
		Assert.assertEquals(alphabet, individualInstance.getAlphabet());
		Assert.assertEquals(25, individualInstance.getFitness(), 0.0001);
	}
}
