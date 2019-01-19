package com.topspin.boot.service;

import com.topspin.boot.domain.Contabilizacao;

public interface ContabilizacaoService {

	void salva(Contabilizacao contabilizacao, String tipo);

	int countContabilizacaoGeralDeAvaliacoesAceitasPorUsuario(Long idUsuario);
	
	int countContabilizacaoGeralDeAvaliacoesRecusadasPorUsuario(Long idUsuario);
	
}
