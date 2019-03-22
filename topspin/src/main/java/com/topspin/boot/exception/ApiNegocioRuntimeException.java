package com.topspin.boot.exception;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ApiNegocioRuntimeException extends RuntimeException {
		
	private HttpStatus status;
	private String descricao;

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
	public ApiNegocioRuntimeException(String message, HttpStatus status, String descricao) {
		super(message);
		this.status = status;
		this.descricao = descricao;
	}
	public ApiNegocioRuntimeException(String message, HttpStatus status, String descricao, Object... args) {
		super(MessageFormat.format(message, args));
		this.status = status;
		this.descricao = descricao;
	}	
	
	//métodos acessores
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
