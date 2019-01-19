package com.topspin.boot.domain;

import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="JOGO")
public class Jogo extends AbstractEntity<Long> {

	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	
	@Column(name = "tipo", nullable = false, length = 30)
	private String tipo;
	
	@Column(name = "resultado", nullable = false, length = 1)
	private String resultado;
	
	@Column(name = "placar", nullable = false, length = 5)
	private String placar;
	
	@Column(name = "qtdTieVencidos")
	private int qtdTieVencidos;
	
	@Column(name = "qtdTiePerdidos")
	private int qtdTiePerdidos;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_adversario")
	private Usuario adversario;
	
	public Jogo() {}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getAdversario() {
		return adversario;
	}

	public void setAdversario(Usuario adversario) {
		this.adversario = adversario;
	}
	
}
