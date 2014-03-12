package com.dobmob.doblist.exceptions;

public class NoListviewException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106305495703717878L;

	public NoListviewException() {
		super();
	}

	public NoListviewException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NoListviewException(String detailMessage) {
		super(detailMessage);
	}

	public NoListviewException(Throwable throwable) {
		super(throwable);
	}

}
