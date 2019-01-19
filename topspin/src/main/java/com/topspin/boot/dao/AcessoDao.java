package com.topspin.boot.dao;

import com.topspin.boot.domain.Acesso;

public interface AcessoDao {
	
	void save(Acesso acesso);
	
	void update(Acesso acesso);
	
	void delete(Long id);
	
	Acesso findById(Long id);
	
	boolean isExisteUsuario(Acesso acesso);

}
