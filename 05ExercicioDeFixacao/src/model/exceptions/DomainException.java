package model.exceptions;

public class DomainException extends Exception {
	/*
	 * attributes section
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * constructors section
	 */
	// constructor - overload
	public DomainException(String msg) {
		super(msg);
	}
}