package com.topspin.boot.dao;

import java.util.List;

import com.topspin.boot.domain.Avaliacao;

public interface AvaliacaoDao {
	
	void save(Avaliacao avaliacao);
	
	void update(Avaliacao avaliacao);
	
	void delete(Long id);
	
	Avaliacao findById(Long id);

	List<Avaliacao> listaPorAvaliadoEStatus(Avaliacao avaliacao);
	
	int countPorAvaliadoEPendente(Avaliacao avaliacao);

}
