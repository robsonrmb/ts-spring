package com.topspin.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topspin.boot.dao.TipoEstatisticaDao;
import com.topspin.boot.domain.TipoEstatistica;

@Service @Transactional(readOnly = false)
public class TipoEstatisticaServiceImpl implements TipoEstatisticaService {

	@Autowired
	private TipoEstatisticaDao tipoEstatisticaDao;
	
	@Override @Transactional(readOnly = true)
	public TipoEstatistica buscaPorNome(String nome) {
		return tipoEstatisticaDao.buscaPorNome(nome);
	}

}
