package de.tudarmstadt.gdi1.project.exception;

public class InvalidCharacterException extends RuntimeException {

	private static final long serialVersionUID = -7376153825239324479L;

	/**
	 * Constructs an <code>InvalidKeyException</code> with no detail message.
	 */
	public InvalidCharacterException() {
		super();
	}

	/**
	 * Constructs an <code>InvalidKeyException</code> with the specified detail
	 * message.
	 * 
	 * @param s
	 *            the detail message.
	 */
	public InvalidCharacterException(String s) {
		super(s);
	}
}
