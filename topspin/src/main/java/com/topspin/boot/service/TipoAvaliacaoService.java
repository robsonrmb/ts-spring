package com.topspin.boot.service;

import com.topspin.boot.domain.TipoAvaliacao;

public interface TipoAvaliacaoService {

	TipoAvaliacao buscaPorNome(String nome);
	
	TipoAvaliacao buscaPorId(Long id);
	
}
