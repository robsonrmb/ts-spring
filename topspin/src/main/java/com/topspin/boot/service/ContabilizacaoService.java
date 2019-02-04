package com.topspin.boot.service;

import com.topspin.boot.domain.Contabilizacao;

public interface ContabilizacaoService {

	void salva(Contabilizacao contabilizacao, String tipo);

	int countContabilizacaoGeralDeAvaliacoesAceitasPorUsuario(Long idUsuario);
	
	int countContabilizacaoGeralDeAvaliacoesRecusadasPorUsuario(Long idUsuario);

	int countContabilizacaoGeralDeConvitesRecebidosAceitosPorUsuario(Long id);

	int countContabilizacaoGeralDeConvitesRecebidosRecusadosPorUsuario(Long id);

	int countContabilizacaoGeralDeConvitesEnviadosPorUsuario(Long id);

	int countContabilizacaoGeralDeJogosRealizadosPorUsuario(Long id);
	
}
