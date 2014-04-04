package com.dobmob.doblist.exceptions;

public class NoListViewException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106305495703717878L;

	public NoListViewException() {
		super();
	}

	public NoListViewException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NoListViewException(String detailMessage) {
		super(detailMessage);
	}

	public NoListViewException(Throwable throwable) {
		super(throwable);
	}

}
