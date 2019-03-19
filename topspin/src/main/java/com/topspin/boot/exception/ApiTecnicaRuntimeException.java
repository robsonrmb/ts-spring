package com.topspin.boot.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ApiTecnicaRuntimeException extends RuntimeException{

	private HttpStatus status;

	public ApiTecnicaRuntimeException() {
		super();
	}
	
	public ApiTecnicaRuntimeException(String message) {
		super(message);
	}	
	public ApiTecnicaRuntimeException(String message, Object... args) {
		super(MessageFormat.format(message, args));
	}	
	public ApiTecnicaRuntimeException(Throwable cause) {
		super(cause);
	}
	public ApiTecnicaRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}	
	public ApiTecnicaRuntimeException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}	
	public ApiTecnicaRuntimeException(String message, HttpStatus status, Object... args) {
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
