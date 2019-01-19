package com.topspin.boot.service;

import com.topspin.boot.domain.Estatistica;

public interface EstatisticaService {

	void salva(Estatistica estatistica);

	int buscaEstatistica(Long idUsuario, String tipoDaEstatistica);
	
	int buscaEstatistica(Long idUsuario, long idTipoEstatistica, long idTipoRespostaEstatistica);
	
}
