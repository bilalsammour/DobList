package com.dobmob.doblist.exceptions;

public class ListViewNotAttachedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106305495703717878L;

	public ListViewNotAttachedException() {
		super();
	}

	public ListViewNotAttachedException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public ListViewNotAttachedException(String detailMessage) {
		super(detailMessage);
	}

	public ListViewNotAttachedException(Throwable throwable) {
		super(throwable);
	}

}
