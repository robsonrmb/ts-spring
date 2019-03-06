package com.topspin.boot.service;

import java.util.List;

import com.topspin.boot.bean.FormAvaliacao;
import com.topspin.boot.bean.FormAvaliacaoResult;
import com.topspin.boot.domain.Avaliacao;

public interface AvaliacaoService {

	void salva(FormAvaliacao formAvaliacao);

	void salvaRespostas(FormAvaliacaoResult formAvaliacaoResult);
	
	void atualiza(FormAvaliacao formAvaliacao, String status);
	
	void exclui(Long id);

	List<Avaliacao> listaPorAvaliadoEStatus(Avaliacao avaliacao);
	
	int countPorAvaliadoEPendente(Avaliacao avaliacao);
	
}
