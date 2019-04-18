package com.topspin.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topspin.boot.dao.TipoRespostaEstatisticaDao;
import com.topspin.boot.domain.TipoRespostaEstatistica;

@Service 
@Transactional(readOnly = false)
public class TipoRespostaEstatisticaServiceImpl {

	@Autowired
	private TipoRespostaEstatisticaDao tipoRespostaEstatisticaDao;
	
	@Transactional(readOnly = true)
	public TipoRespostaEstatistica buscaPorNome(String nome) {
		return tipoRespostaEstatisticaDao.buscaPorNome(nome);
	}

	public TipoRespostaEstatistica findById(long id) {
		return tipoRespostaEstatisticaDao.findById(id);
	}

}
