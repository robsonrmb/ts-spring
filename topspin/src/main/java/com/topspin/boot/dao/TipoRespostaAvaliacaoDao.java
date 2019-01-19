package com.topspin.boot.dao;

import com.topspin.boot.domain.TipoRespostaAvaliacao;

public interface TipoRespostaAvaliacaoDao {
	
	TipoRespostaAvaliacao findById(Long id);
	
	TipoRespostaAvaliacao buscaPorNome(String nome);

}
