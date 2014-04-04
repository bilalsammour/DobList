package com.dobmob.doblist.exceptions;

public class NoEmptyViewException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106305495703717878L;

	public NoEmptyViewException() {
		super();
	}

	public NoEmptyViewException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NoEmptyViewException(String detailMessage) {
		super(detailMessage);
	}

	public NoEmptyViewException(Throwable throwable) {
		super(throwable);
	}

}
