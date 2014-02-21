package de.tudarmstadt.gdi1.project.exception;

public class InvalidKeyException extends RuntimeException {

	private static final long serialVersionUID = -312242687954368892L;

	/**
	 * Constructs an <code>InvalidKeyException</code> with no detail message.
	 */
	public InvalidKeyException() {
		super();
	}

	/**
	 * Constructs an <code>InvalidKeyException</code> with the specified detail
	 * message.
	 * 
	 * @param s
	 *            the detail message.
	 */
	public InvalidKeyException(String s) {
		super(s);
	}
}
