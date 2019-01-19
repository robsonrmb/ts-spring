package com.topspin.boot.dao;

import com.topspin.boot.domain.TipoRespostaEstatistica;

public interface TipoRespostaEstatisticaDao {
	
	TipoRespostaEstatistica findById(Long id);
	
	TipoRespostaEstatistica buscaPorNome(String nome);

}
