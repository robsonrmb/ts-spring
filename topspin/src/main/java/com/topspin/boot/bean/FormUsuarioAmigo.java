package com.topspin.boot.bean;

public class FormUsuarioAmigo {

	private long id;
	private long idUsuario;
	private long idAmigo;
	
	public FormUsuarioAmigo() {}
	
	public FormUsuarioAmigo(long id, long idUsuario, long idAmigo) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idAmigo = idAmigo;
	}
	
	public FormUsuarioAmigo(long idUsuario, long idAmigo) {
		super();
		this.idUsuario = idUsuario;
		this.idAmigo = idAmigo;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdAmigo() {
		return idAmigo;
	}
	public void setIdAmigo(long idAmigo) {
		this.idAmigo = idAmigo;
	}
	
}
