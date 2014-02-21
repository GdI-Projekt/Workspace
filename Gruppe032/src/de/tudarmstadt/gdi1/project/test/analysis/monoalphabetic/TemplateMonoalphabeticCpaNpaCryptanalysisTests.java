package de.tudarmstadt.gdi1.project.test.analysis.monoalphabetic;

import org.junit.Assert;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.EncryptionOracle;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.MonoalphabeticCpaNpaCryptanalysis;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.MonoalphabeticCipher;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateMonoalphabeticCpaNpaCryptanalysisTests {

	@Test
	public void testChosenPlaintextAttack() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		final MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, target);

		MonoalphabeticCpaNpaCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCpaNpaCryptanalysis();
		char[] recoveredKey = ca.chosenPlaintextAttack(new EncryptionOracle<MonoalphabeticCipher>() {

			@Override
			public String encrypt(String plaintext) {
				return cipher.encrypt(plaintext);
			}
		}, source);

		Assert.assertEquals(0, TemplateTestUtils.countDifferences(target.asCharArray(), recoveredKey));
	}

	@Test
	public void testChosenPlaintextAttackWithRandom() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestCore.getFactory().getUtilsInstance().randomizeAlphabet(source);

		final MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, target);

		MonoalphabeticCpaNpaCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCpaNpaCryptanalysis();
		char[] recoveredKey = ca.chosenPlaintextAttack(new EncryptionOracle<MonoalphabeticCipher>() {

			@Override
			public String encrypt(String plaintext) {
				return cipher.encrypt(plaintext);
			}
		}, source);

		Assert.assertEquals(0, TemplateTestUtils.countDifferences(target.asCharArray(), recoveredKey));
	}

	@Test
	public void testSimpleKnownPlaintextAttak() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		final MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, target);
		String plaintext = source.normalize("abcdefghijklmnopqrstuvwxyz");
		String ciphertext = cipher.encrypt(plaintext);

		MonoalphabeticCpaNpaCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCpaNpaCryptanalysis();
		char[] recoveredKey = ca.knownPlaintextAttack(ciphertext, plaintext, source);

		Assert.assertEquals(0, TemplateTestUtils.countDifferences(target.asCharArray(), recoveredKey));
	}

	@Test
	public void testMoreComplexKnownPlaintextAttak() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		final MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, target);
		String plaintext = source.normalize(TemplateTestUtils.ALICE_PLAIN);
		String ciphertext = cipher.encrypt(plaintext);

		MonoalphabeticCpaNpaCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCpaNpaCryptanalysis();
		char[] recoveredKey = ca.knownPlaintextAttack(ciphertext, plaintext, source);

		Assert.assertEquals(4, TemplateTestUtils.countDifferences(target.asCharArray(), recoveredKey));
	}

	@Test
	public void testReferredImplementeationKnownPlaintextAttak() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		final MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, target);
		String plaintext = source.normalize("abcdefghijklmnopqrstuvwxyz");
		String ciphertext = cipher.encrypt(plaintext);

		MonoalphabeticCpaNpaCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCpaNpaCryptanalysis();
		Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, "", 1);
		char[] recoveredKey = (char[]) ca.knownPlaintextAttack(ciphertext, plaintext, distribution);

		Assert.assertEquals(0, TemplateTestUtils.countDifferences(target.asCharArray(), recoveredKey));
	}
}
