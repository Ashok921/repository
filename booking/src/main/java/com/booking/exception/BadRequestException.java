package com.booking.exception;

public class BadRequestException extends Exception{

	// custom exception to be thrown when there is invalid input
	private static final long serialVersionUID = 1L;

	public BadRequestException(String errorMessage) {
		super(errorMessage);
	}
}
