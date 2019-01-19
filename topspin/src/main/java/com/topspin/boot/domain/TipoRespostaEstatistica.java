package com.topspin.boot.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="TIPO_RESPOSTA_ESTATISTICA")
public class TipoRespostaEstatistica extends AbstractEntity<Long> {

	// ruim | regular | bom | ótimo ...
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	public TipoRespostaEstatistica() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
