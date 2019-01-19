package com.topspin.boot.dao;

import java.util.List;

import com.topspin.boot.domain.Estatistica;

public interface EstatisticaDao {
	
	void save(Estatistica estatistica);
	
	void update(Estatistica estatistica);
	
	List<Estatistica> buscaPorUsuarioAnoTipoEResposta(long idUsuario, int ano, long idTipoEstatistica, long idTipoRespostaEstatistica);
	
}
