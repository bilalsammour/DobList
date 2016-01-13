package com.dobmob.doblist.exceptions;

public class EmptyViewNotAttachedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106305495703717878L;

	public EmptyViewNotAttachedException() {
		super();
	}

	public EmptyViewNotAttachedException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public EmptyViewNotAttachedException(String detailMessage) {
		super(detailMessage);
	}

	public EmptyViewNotAttachedException(Throwable throwable) {
		super(throwable);
	}

}
