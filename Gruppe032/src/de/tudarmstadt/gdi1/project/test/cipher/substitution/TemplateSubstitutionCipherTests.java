package de.tudarmstadt.gdi1.project.test.cipher.substitution;

import static org.mockito.Matchers.anyChar;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import de.tudarmstadt.gdi1.project.cipher.substitution.SubstitutionCipher;
import de.tudarmstadt.gdi1.project.test.TemplateTestCore;

public class TemplateSubstitutionCipherTests {

	@Test
	public void testTemplateSubstitutionCipherAbstract() {
		Class<? extends SubstitutionCipher> clazz = TemplateTestCore.getFactory().getAbstractSubstitutionCipherClass();
		Assert.assertTrue(Modifier.isAbstract(clazz.getModifiers()));
	}

	@Test
	public void testTemplateSubstitutionCipherEncryptionDecryption() {
		Class<? extends SubstitutionCipher> clazz = TemplateTestCore.getFactory().getAbstractSubstitutionCipherClass();

		SubstitutionCipher mockedCipher = mock(clazz);
		/* when(mockedCipher.encrypt("abcdef")).thenCallRealMethod(); */
		/*
		 * encrypt needs to be final for this to work, otherwise you need
		 * additionally the above line.
		 */
		mockedCipher.encrypt("abcdef");

		verify(mockedCipher, times(6)).translate(anyChar(), anyInt());
		verify(mockedCipher, times(0)).reverseTranslate(anyChar(), anyInt());

		/* when(mockedCipher.decrypt("abcdef")).thenCallRealMethod(); */
		/*
		 * encrypt needs to be final for this to work, otherwise you need
		 * additionally the above line.
		 */
		mockedCipher.decrypt("abcdef");
		verify(mockedCipher, times(6)).reverseTranslate(anyChar(), anyInt());
	}

	@Test
	public void testTemplateSubstitutionCipherEncryption() {
		Class<? extends SubstitutionCipher> clazz = TemplateTestCore.getFactory().getAbstractSubstitutionCipherClass();

		SubstitutionCipher mockedCipher = mock(clazz);
		when(mockedCipher.translate(anyChar(), anyInt())).thenReturn('b').thenReturn('a');

		/* when(mockedCipher.encrypt("abc")).thenCallRealMethod(); */
		/*
		 * encrypt needs to be final for this to work, otherwise you need
		 * additionally the above line.
		 */
		Assert.assertEquals("baa", mockedCipher.encrypt("abc"));

		verify(mockedCipher).translate('a', 0);
		verify(mockedCipher).translate('b', 1);
		verify(mockedCipher).translate('c', 2);
	}

}
