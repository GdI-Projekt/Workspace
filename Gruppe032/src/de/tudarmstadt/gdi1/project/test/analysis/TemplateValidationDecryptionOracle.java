package de.tudarmstadt.gdi1.project.test.analysis;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.ValidateDecryptionOracle;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateValidationDecryptionOracle {

	@Test
	public void testSuccess() throws IOException {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();

		String alice = TemplateTestUtils.ALICE;

		final Dictionary dictionary = TemplateTestCore.getFactory().getDictionaryInstance(source, alice);
		final Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, alice, 3);

		ValidateDecryptionOracle testPlaintextOracleIM = TemplateTestCore.getFactory()
				.getValidateDecryptionOracle(distribution, dictionary);

		Assert.assertTrue(testPlaintextOracleIM.isCorrect(source.normalize(TemplateTestUtils.ALICE_PLAIN)));
	}

	@Test
	public void testFailure() throws IOException {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();

		String alice = TemplateTestUtils.ALICE;

		final Dictionary dictionary = TemplateTestCore.getFactory().getDictionaryInstance(source, alice);
		final Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, alice, 3);

		ValidateDecryptionOracle testPlaintextOracleIM = TemplateTestCore.getFactory()
				.getValidateDecryptionOracle(distribution, dictionary);

		Assert.assertFalse(testPlaintextOracleIM.isCorrect(source
				.normalize("kagshd sadkfu fkasjf suiadhfjas jaskdf ajssjnd asdfnuasn ffnulgldfmösd iluhads fnas flasdf nljasfou asdf")));
	}
}
