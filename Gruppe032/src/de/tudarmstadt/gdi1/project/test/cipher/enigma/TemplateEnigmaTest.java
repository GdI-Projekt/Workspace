package de.tudarmstadt.gdi1.project.test.cipher.enigma;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import de.tudarmstadt.gdi1.project.Factory;
import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.cipher.enigma.Enigma;
import de.tudarmstadt.gdi1.project.cipher.enigma.PinBoard;
import de.tudarmstadt.gdi1.project.cipher.enigma.ReverseRotor;
import de.tudarmstadt.gdi1.project.cipher.enigma.Rotor;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;
import de.tudarmstadt.gdi1.project.test.TemplateTestUtils;

public class TemplateEnigmaTest {
	private Factory f = TemplateTestCore.getFactory();

	@Test
	public void simpleTest() {
		Rotor r1 = f.getRotorInstance(TemplateTestUtils.getDefaultAlphabet(), TemplateTestUtils.getDefaultAlphabet(), 0);
		List<Rotor> rotorlist = new LinkedList<>();
		rotorlist.add(r1);
		PinBoard pb = f.getPinBoardInstance(TemplateTestUtils.getDefaultAlphabet(), TemplateTestUtils.getDefaultAlphabet());
		ReverseRotor rr = f.getReverseRotatorInstance(TemplateTestUtils.getDefaultAlphabet(),
				TemplateTestUtils.getReversedDefaultAlphabet());
		Enigma e = f.getEnigmaInstance(rotorlist, pb, rr);
		assertEquals("hallowelt", e.decrypt(e.encrypt("hallowelt")));
	}

	@Test
	public void smallAlphabetSingleRotorTest() {
		Alphabet rotor1Alphabet = f.getAlphabetInstance(Arrays.asList('b', 'c', 'a', 'd'));
		Rotor r1 = f.getRotorInstance(TemplateTestUtils.getMinimalAlphabet(), rotor1Alphabet, 0);
		List<Rotor> rotorList = new LinkedList<>();
		rotorList.add(r1);
		PinBoard pb = f.getPinBoardInstance(TemplateTestUtils.getMinimalAlphabet(), TemplateTestUtils.getMinimalAlphabet());
		ReverseRotor rr = f.getReverseRotatorInstance(TemplateTestUtils.getMinimalAlphabet(),
				TemplateTestUtils.getReversedMinimalAlphabet());
		Enigma e = f.getEnigmaInstance(rotorList, pb, rr);
		assertEquals("bc", e.encrypt("aa"));
	}
}
