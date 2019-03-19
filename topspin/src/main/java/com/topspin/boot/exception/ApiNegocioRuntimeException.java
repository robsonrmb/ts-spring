package com.topspin.boot.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ApiNegocioRuntimeException extends RuntimeException {
		
	private HttpStatus status;

	public ApiNegocioRuntimeException() {
		super();
	}
	
	public ApiNegocioRuntimeException(String message) {
		super(message);
	}	
	public ApiNegocioRuntimeException(String message, Object... args) {
		super(MessageFormat.format(message, args));
	}	
	public ApiNegocioRuntimeException(Throwable cause) {
		super(cause);
	}
	public ApiNegocioRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}	
	public ApiNegocioRuntimeException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}	
	public ApiNegocioRuntimeException(String message, HttpStatus status, Object... args) {
		super(MessageFormat.format(message, args));
		this.status = status;
	}	
	
	//métodos acessores
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
