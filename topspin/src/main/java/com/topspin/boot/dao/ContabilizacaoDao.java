package com.topspin.boot.dao;

import java.util.List;

import com.topspin.boot.domain.Contabilizacao;

public interface ContabilizacaoDao {
	
	void save(Contabilizacao contabilizacao);
	
	void update(Contabilizacao contabilizacao);
	
	List<Contabilizacao> buscaPorUsuarioAno(long idUsuario, int ano);
	
}
