package com.parivesh.exceptions;

public class PariveshException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PariveshException() {
		super();
	}

	/**
	 * Constructor
	 */
	public PariveshException(String msg) {
		super(msg);

	}

	/**
	 * Override finalize()
	 */
	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}

}
