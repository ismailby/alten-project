package com.alten.back.constants;

import org.springframework.http.HttpStatus;

/**
 * Cette classe contient des constantes globales utilisées dans l'application.
 */
public class Constants {
	public static final String ERROR_400 = "400";
	public static final String ERROR_409 = "409";
	public static final String ERROR_500 = "500";
	public static final String ERROR_404 = "404";
	public static final String ERROR_403 = "403";
	public static final String ERROR_401 = "401";
	
	public static final String ERROR = "error";
	public static final String MESSAGE = "message";
	public static final String STATUS = "status";
	public static final HttpStatus HTTP_STATUS_CONFLICT = HttpStatus.CONFLICT;
	public static final HttpStatus HTTP_STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST;
	public static final HttpStatus HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;

}