package com.topspin.boot.bean;

import java.util.Date;

public class FormJogo {

	private long id;
	private long idUsuario;
	private long idAdversario;
	private Date data;
	private String tipo;
	private String resultado;
	private String placar;
	private int qtdTieVencidos;
	private int qtdTiePerdidos;
	private String ultimosJogos;
	private String dataJogoFormatada;
	
	public FormJogo() {}

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

	public long getIdAdversario() {
		return idAdversario;
	}

	public void setIdAdversario(long idAdversario) {
		this.idAdversario = idAdversario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getPlacar() {
		return placar;
	}

	public void setPlacar(String placar) {
		this.placar = placar;
	}

	public int getQtdTieVencidos() {
		return qtdTieVencidos;
	}

	public void setQtdTieVencidos(int qtdTieVencidos) {
		this.qtdTieVencidos = qtdTieVencidos;
	}

	public int getQtdTiePerdidos() {
		return qtdTiePerdidos;
	}

	public void setQtdTiePerdidos(int qtdTiePerdidos) {
		this.qtdTiePerdidos = qtdTiePerdidos;
	}

	public String getResultadoFormatado() {
		if (getResultado() != null) {
			if (getResultado().equals("V")) {
				return "VITORIA";
				
			} else if (getResultado().equals("D")) {
				return "DERROTA";
			
			}else {
				return "";
			}
		}else {
			return "";
		}
	}

	public String getUltimosJogos() {
		return ultimosJogos;
	}

	public void setUltimosJogos(String ultimosJogos) {
		this.ultimosJogos = ultimosJogos;
	}

	public String getDataJogoFormatada() {
		return dataJogoFormatada;
	}

	public void setDataJogoFormatada(String dataJogoFormatada) {
		this.dataJogoFormatada = dataJogoFormatada;
	}
	
}
