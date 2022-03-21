package com.booking.exception;

public class DataNotFoundException extends Exception {

	// Custom exception to be thrown when there is no data in db

	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
