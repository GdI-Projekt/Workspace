package de.tudarmstadt.gdi1.project.exception;

public class InvalidAlphabetException extends RuntimeException {

	private static final long serialVersionUID = -1332737148112953110L;

	/**
	 * Constructs an <code>InvalidAlphabetException</code> with no detail
	 * message.
	 */
	public InvalidAlphabetException() {
		super();
	}

	/**
	 * Constructs an <code>InvalidAlphabetException</code> with the specified
	 * detail message.
	 * 
	 * @param s
	 *            the detail message.
	 */
	public InvalidAlphabetException(String s) {
		super(s);
	}
}
