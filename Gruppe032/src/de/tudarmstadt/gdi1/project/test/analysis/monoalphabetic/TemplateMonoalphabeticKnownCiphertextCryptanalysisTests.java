package de.tudarmstadt.gdi1.project.test.analysis.monoalphabetic;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;
import de.tudarmstadt.gdi1.project.alphabet.Distribution;
import de.tudarmstadt.gdi1.project.analysis.ValidateDecryptionOracle;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.GeneticAnalysis;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.Individual;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.MonoalphabeticKnownCiphertextCryptanalysis;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.MonoalphabeticCipher;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateMonoalphabeticKnownCiphertextCryptanalysisTests {

	@Test
	public void implementsGenetic() {
		MonoalphabeticKnownCiphertextCryptanalysis ca = TemplateTestCore.getFactory()
				.getMonoalphabeticKnownCiphertextCryptanalysisInstance();
		Assert.assertTrue(ca instanceof GeneticAnalysis);
	}

	@Test
	public void stateImplemented() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		MonoalphabeticKnownCiphertextCryptanalysis ca = TemplateTestCore.getFactory()
				.getMonoalphabeticKnownCiphertextCryptanalysisInstance();
		Assert.assertFalse("".equals(ca.getState(source, target)));
		Assert.assertFalse(null == ca.getState(source, target));
	}

	@Test
	public void testInitialGeneration() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		/* generate distribution */
		Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, TemplateTestUtils.ALICE, 3);

		/* get ciphertext */
		String plaintext = source.normalize(TemplateTestUtils.ALICE_PLAIN);
		MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, target);
		String ciphertext = cipher.encrypt(plaintext);

		MonoalphabeticKnownCiphertextCryptanalysis ca = TemplateTestCore.getFactory()
				.getMonoalphabeticKnownCiphertextCryptanalysisInstance();

		GeneticAnalysis ga = (GeneticAnalysis) ca;

		List<Individual> generation = ga.prepareInitialGeneration(ciphertext, source, distribution, 15);
		Assert.assertEquals(15, generation.size());
		Assert.assertTrue(generation.get(5) instanceof Individual);
	}

	@Test
	public void testSurvivors() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		/* generate distribution */
		Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, TemplateTestUtils.ALICE, 3);
		Dictionary dictionary = TemplateTestCore.getFactory().getDictionaryInstance(source, TemplateTestUtils.ALICE);

		/* get ciphertext */
		String plaintext = source.normalize(TemplateTestUtils.ALICE_PLAIN);
		MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, target);
		String ciphertext = cipher.encrypt(plaintext);

		MonoalphabeticKnownCiphertextCryptanalysis ca = TemplateTestCore.getFactory()
				.getMonoalphabeticKnownCiphertextCryptanalysisInstance();

		GeneticAnalysis ga = (GeneticAnalysis) ca;

		List<Individual> generation = ga.prepareInitialGeneration(ciphertext, source, distribution, 15);
		List<Individual> survivors = ga.computeSurvivors(ciphertext, source, generation, distribution, dictionary, 3);
		Assert.assertEquals(3, survivors.size());
	}

	@Test
	public void testFitness() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		/* generate distribution */
		Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, TemplateTestUtils.ALICE, 3);
		Dictionary dictionary = TemplateTestCore.getFactory().getDictionaryInstance(source, TemplateTestUtils.ALICE);

		/* get ciphertext */
		String plaintext = source.normalize(TemplateTestUtils.ALICE_PLAIN);
		MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, target);
		String ciphertext = cipher.encrypt(plaintext);

		MonoalphabeticKnownCiphertextCryptanalysis ca = TemplateTestCore.getFactory()
				.getMonoalphabeticKnownCiphertextCryptanalysisInstance();

		GeneticAnalysis ga = (GeneticAnalysis) ca;

		List<Individual> generation = ga.prepareInitialGeneration(ciphertext, source, distribution, 15);
		double fitness = ga.computeFitness(generation.get(5), ciphertext, source, distribution, dictionary);

		Assert.assertTrue(fitness >= 0);
	}

	@Test
	public void testFullAnalysis() throws IOException, InterruptedException, ExecutionException {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		/* generate distribution */
		final Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, TemplateTestUtils.ALICE, 3);
		final Dictionary dictionary = TemplateTestCore.getFactory().getDictionaryInstance(source, TemplateTestUtils.ALICE);

		/* get ciphertext */
		final String plaintext = source.normalize(TemplateTestUtils.ALICE_PLAIN);
		MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, target);
		final String ciphertext = cipher.encrypt(plaintext);

		final MonoalphabeticKnownCiphertextCryptanalysis ca = TemplateTestCore.getFactory()
				.getMonoalphabeticKnownCiphertextCryptanalysisInstance();

		/* run break in thread */
		Callable<char[]> task = new Callable<char[]>() {

			@Override
			public char[] call() throws Exception {
				char[] reconstructedKey = ca.knownCiphertextAttack(ciphertext, distribution, dictionary
						, new ValidateDecryptionOracle() {
							@Override
							public boolean isCorrect(String p) {
								return plaintext.equals(p);
							}
						}
						);
				return reconstructedKey;
			}
		};
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<char[]> future = service.submit(task);

		long t = System.currentTimeMillis();
		while (!future.isDone()) {
			Thread.sleep(5000);
			System.out.println(ca.getState(distribution.getAlphabet(), target));
		}

		char[] reconstructedKey = future.get();

		System.out.println("-- reconstruction finished in " + (System.currentTimeMillis() - t) + "ms");
		System.out.println(reconstructedKey);
		cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, TemplateTestUtils.getAlphabetFrom(reconstructedKey));
		String plaintextPrime = cipher.decrypt(ciphertext);

		if (!plaintextPrime.equals(plaintext))
			Assert.assertEquals(0, TemplateTestUtils.countDifferences(target.asCharArray(), reconstructedKey));
	}
}
