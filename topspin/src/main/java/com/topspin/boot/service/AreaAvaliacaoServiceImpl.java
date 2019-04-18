package com.topspin.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topspin.boot.dao.AreaAvaliacaoDao;
import com.topspin.boot.domain.AreaAvaliacao;
import com.topspin.boot.domain.TipoAvaliacao;

@Service @Transactional(readOnly = false)
public class AreaAvaliacaoServiceImpl {

	@Autowired
	private AreaAvaliacaoDao areaAvaliacaoDao;

	public List<AreaAvaliacao> listaAreasAvaliacaoCompleto() {
		ArrayList<AreaAvaliacao> listaDeAreasAvaliacoes = (ArrayList<AreaAvaliacao>) areaAvaliacaoDao.listaAreasAvaliacaoCompleto();
		for (AreaAvaliacao aa: listaDeAreasAvaliacoes) {
			for (TipoAvaliacao ta: aa.getTipo()) {
				ta.getTipoRespostas().size();
			}
		}
		return listaDeAreasAvaliacoes;
	}
	
}
