package com.topspin.boot.dao;

import com.topspin.boot.domain.TipoAvaliacao;

public interface TipoAvaliacaoDao {
	
	TipoAvaliacao buscaPorNome(String nome);
	
	TipoAvaliacao findById(Long id);

}
