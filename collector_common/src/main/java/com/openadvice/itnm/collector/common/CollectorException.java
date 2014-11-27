package com.openadvice.itnm.collector.common;

public class CollectorException extends RuntimeException {
	private static final long serialVersionUID = 6751374393189926980L;

	public CollectorException(String message) {
		super(message);
	}

	public CollectorException(Throwable cause) {
		super(cause);
	}

	public CollectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
