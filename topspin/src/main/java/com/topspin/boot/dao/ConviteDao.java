package com.topspin.boot.dao;

import java.util.List;

import com.topspin.boot.domain.Convite;

public interface ConviteDao {
	
	void save(Convite convite);
	
	void update(Convite convite);
	
	void delete(Long id);
	
	Convite findById(Long id);
	
	List<Convite> findAll();
	
	List<Convite> listaPorUsuarioEStatus(Convite convite);
	
	List<Convite> listaPorConvidadoEStatus(Convite convite);

	List<Convite> listaPorConvidadoENaoPendentes(Convite convite);
	
	int countPorConvidadoEPendentes(Convite convite);

}
