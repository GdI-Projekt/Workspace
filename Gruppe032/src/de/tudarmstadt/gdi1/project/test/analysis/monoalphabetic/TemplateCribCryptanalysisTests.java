package de.tudarmstadt.gdi1.project.test.analysis.monoalphabetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.BacktrackingAnalysis;
import de.tudarmstadt.gdi1.project.analysis.monoalphabetic.MonoalphabeticCribCryptanalysis;
import de.tudarmstadt.gdi1.project.cipher.substitution.monoalphabetic.MonoalphabeticCipher;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateCribCryptanalysisTests {

	@Test
	public void implementsBacktracking() {
		MonoalphabeticCribCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCribCryptanalysisInstance();
		Assert.assertTrue(ca instanceof BacktrackingAnalysis);
	}

	@Test
	public void stateImplemented() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		Alphabet target = TemplateTestUtils.getMixedDefaultAlphabet();

		MonoalphabeticCribCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCribCryptanalysisInstance();
		Assert.assertFalse("".equals(ca.getState(source, target)));
		Assert.assertFalse(null == ca.getState(source, target));
	}

	@Test
	public void testNextSourceChar() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();

		Dictionary dictionary = TemplateTestCore.getFactory().getDictionaryInstance(source, TemplateTestUtils.ALICE);
		Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, TemplateTestUtils.ALICE, 3);

		MonoalphabeticCribCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCribCryptanalysisInstance();
		BacktrackingAnalysis ba = (BacktrackingAnalysis) ca;

		Map<Character, Character> partialKey = constructPartialKey(source);
		Character nextChar = ba.getNextSourceChar(partialKey, source, distribution, dictionary, new ArrayList<String>());

		Assert.assertTrue(source.contains(nextChar));
		Assert.assertFalse(partialKey.containsKey(nextChar));
	}

	@Test
	public void testPotentialAssignment() {
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();

		Dictionary dictionary = TemplateTestCore.getFactory().getDictionaryInstance(source, TemplateTestUtils.ALICE);
		Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, TemplateTestUtils.ALICE, 3);

		MonoalphabeticCribCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCribCryptanalysisInstance();
		BacktrackingAnalysis ba = (BacktrackingAnalysis) ca;

		Map<Character, Character> partialKey = constructPartialKey(source);
		Collection<Character> assignments = ba.getPotentialAssignments('w', partialKey, source.normalize(TemplateTestUtils.ALICE_PLAIN),
				source, distribution, dictionary);

		for (char c : assignments)
			Assert.assertTrue(source.contains(c));

		Assert.assertEquals(source.size() - partialKey.size(), assignments.size());
	}

	@Test
	public void testBasicBacktrackingAttack() throws InterruptedException, ExecutionException {
		/* this can run for a while */
		Alphabet source = TemplateTestUtils.getAlphabetFrom(new char[] { 'a', 'e', 'f', 'h', 'm', 'p', 's', 't', 'v', 'z' });
		final Alphabet key = TemplateTestCore.getFactory().getUtilsInstance().randomizeAlphabet(source);

		Alphabet sourceWithBlank = TemplateTestUtils.getAlphabetFrom(new char[] { 'a', 'e', 'f', 'h', 'm', 'p', 's', 't', 'v', 'z', ' ' });

		final Dictionary dictionary = TemplateTestCore.getFactory().getDictionaryInstance(source,
				sourceWithBlank.normalize(TemplateTestUtils.ALICE));
		final Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source,
				sourceWithBlank.normalize(TemplateTestUtils.ALICE), 3);

		final MonoalphabeticCribCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCribCryptanalysisInstance();

		MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, key);
		final String plaintext = source.normalize(TemplateTestUtils.ALICE_PLAIN);
		final String ciphertext = cipher.encrypt(plaintext);

		final List<String> cribs = new ArrayList<>();

		/* run break in thread */
		Callable<char[]> task = new Callable<char[]>() {

			@Override
			public char[] call() throws Exception {
				char[] reconstructedKey = ca.knownCiphertextAttack(ciphertext, distribution, dictionary, cribs,
						new ValidateDecryptionOracle() {
							@Override
							public boolean isCorrect(String p) {
								return plaintext.equals(p);
							}
						});
				return reconstructedKey;
			}
		};
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<char[]> future = service.submit(task);

		long t = System.currentTimeMillis();
		while (!future.isDone()) {
			Thread.sleep(5000);
			System.out.println(ca.getState(distribution.getAlphabet(), key));
		}

		char[] reconstructedKey = future.get();

		System.out.println("-- reconstruction finished in " + (System.currentTimeMillis() - t) + "ms");
		System.out.println(reconstructedKey);

		cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, TemplateTestUtils.getAlphabetFrom(reconstructedKey));
		String plaintextPrime = cipher.decrypt(ciphertext);

		if (!plaintextPrime.equals(plaintext))
			Assert.assertEquals(0, TemplateTestUtils.countDifferences(key.asCharArray(), reconstructedKey));
	}

	@Test
	public void testFullAttack() throws InterruptedException, ExecutionException {
		/* this can run for a while */
		Alphabet source = TemplateTestUtils.getDefaultAlphabet();
		final Alphabet key = TemplateTestUtils.getMixedDefaultAlphabet();

		final Dictionary dictionary = TemplateTestCore.getFactory().getDictionaryInstance(source, TemplateTestUtils.ALICE);
		final Distribution distribution = TemplateTestCore.getFactory().getDistributionInstance(source, TemplateTestUtils.ALICE, 3);

		final MonoalphabeticCribCryptanalysis ca = TemplateTestCore.getFactory().getMonoalphabeticCribCryptanalysisInstance();

		MonoalphabeticCipher cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, key);
		final String plaintext = source.normalize(TemplateTestUtils.ALICE_PLAIN);
		final String ciphertext = cipher.encrypt(plaintext);

		final List<String> cribs = new ArrayList<>();
		cribs.add("alice");
		cribs.add("reizendsten");
		cribs.add("maeuseloch");
		cribs.add("zusammenschieben");

		/* run break in thread */
		Callable<char[]> task = new Callable<char[]>() {

			@Override
			public char[] call() throws Exception {
				char[] reconstructedKey = ca.knownCiphertextAttack(ciphertext, distribution, dictionary, cribs,
						new ValidateDecryptionOracle() {
							@Override
							public boolean isCorrect(String p) {
								return plaintext.equals(p);
							}
						});
				return reconstructedKey;
			}
		};
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<char[]> future = service.submit(task);

		long t = System.currentTimeMillis();
		while (!future.isDone()) {
			Thread.sleep(5000);
			System.out.println(ca.getState(distribution.getAlphabet(), key));
		}

		char[] reconstructedKey = future.get();

		System.out.println("-- reconstruction finished in " + (System.currentTimeMillis() - t) + "ms");
		System.out.println(reconstructedKey);

		cipher = TemplateTestCore.getFactory().getMonoalphabeticCipherInstance(source, TemplateTestUtils.getAlphabetFrom(reconstructedKey));
		String plaintextPrime = cipher.decrypt(ciphertext);

		if (!plaintextPrime.equals(plaintext))
			Assert.assertEquals(0, TemplateTestUtils.countDifferences(key.asCharArray(), reconstructedKey));
	}

	private Map<Character, Character> constructPartialKey(Alphabet source) {
		Map<Character, Character> partialKey = new HashMap<>();
		partialKey.put('a', 'c');
		partialKey.put('b', 'h');
		partialKey.put('c', 'f');
		partialKey.put('z', 'x');
		partialKey.put('s', 't');
		partialKey.put('h', 'g');
		partialKey.put('d', 'l');
		partialKey.put('x', 'y');
		return partialKey;
	}
}
