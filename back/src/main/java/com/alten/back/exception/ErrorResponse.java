package com.alten.back.exception;


import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * ErrorResponse represente la structure d'une reponse d'erreur.
 */

@Data
public class ErrorResponse extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String errorCode;
	private HttpStatus httpStatus;

	public ErrorResponse(String errorMessage, String errorCode, HttpStatus httpStatus) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public ErrorResponse() {
		super();
	}

}
