package com.topspin.boot.service;

import com.topspin.boot.domain.TipoRespostaEstatistica;

public interface TipoRespostaEstatisticaService {

	TipoRespostaEstatistica findById(long id);
	
	TipoRespostaEstatistica buscaPorNome(String nome);
	
}
