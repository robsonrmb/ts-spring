package com.topspin.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topspin.boot.dao.TipoAvaliacaoDao;
import com.topspin.boot.domain.TipoAvaliacao;

@Service @Transactional(readOnly = false)
public class TipoAvaliacaoServiceImpl implements TipoAvaliacaoService {

	@Autowired
	private TipoAvaliacaoDao tipoAvaliacaoDao;
	
	@Override @Transactional(readOnly = true)
	public TipoAvaliacao buscaPorNome(String nome) {
		return tipoAvaliacaoDao.buscaPorNome(nome);
	}

}
