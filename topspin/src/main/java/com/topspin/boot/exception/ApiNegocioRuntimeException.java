package com.topspin.boot.exception;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ApiNegocioRuntimeException extends RuntimeException {
		
	private HttpStatus status;
	private List<String> listaDeMensagens;

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
	public ApiNegocioRuntimeException(String message, HttpStatus status, List<String> listaDeMensagens) {
		super(message);
		this.status = status;
		this.listaDeMensagens = listaDeMensagens;
	}
	public ApiNegocioRuntimeException(String message, HttpStatus status, List<String> listaDeMensagens, Object... args) {
		super(MessageFormat.format(message, args));
		this.status = status;
		this.listaDeMensagens = listaDeMensagens;
	}	
	
	//métodos acessores
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<String> getListaDeMensagens() {
		return listaDeMensagens;
	}

	public void setListaDeMensagens(List<String> listaDeMensagens) {
		this.listaDeMensagens = listaDeMensagens;
	}

}
