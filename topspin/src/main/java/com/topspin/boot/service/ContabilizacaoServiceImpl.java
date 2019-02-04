package com.topspin.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topspin.boot.dao.ContabilizacaoDao;
import com.topspin.boot.domain.Contabilizacao;

@Service @Transactional(readOnly = false)
public class ContabilizacaoServiceImpl implements ContabilizacaoService {

	@Autowired
	private ContabilizacaoDao contabilizacaoDao;
	
	@Override
	public void salva(Contabilizacao contabilizacao, String tipo) {
		
		List<Contabilizacao> listaDeContabilizacao = contabilizacaoDao
			.buscaPorUsuarioAno(contabilizacao.getUsuario().getId(), contabilizacao.getAno());
		
		if (listaDeContabilizacao != null && listaDeContabilizacao.size() > 1) {
			System.out.println("Erro na gravação da contabilizacao... Verificar!!!");
			
		}else if (listaDeContabilizacao.size() == 1) {
			somarMaisUm(listaDeContabilizacao.get(0), tipo);
			
		}else {
			if ("A".equals(tipo)) {
				contabilizacao.setQuantidadeAvaliacaoAceita(1);
			}else {
				contabilizacao.setQuantidadeAvaliacaoRecusada(1);
			}
			contabilizacaoDao.save(contabilizacao);
		}
	}

	private void somarMaisUm(Contabilizacao c, String tipo) {
		if ("A".equals(tipo)) {
			c.setQuantidadeAvaliacaoAceita(c.getQuantidadeAvaliacaoAceita() + 1);
		}else {
			c.setQuantidadeAvaliacaoRecusada(c.getQuantidadeAvaliacaoRecusada() + 1);
		}
	}

	@Override
	public int countContabilizacaoGeralDeAvaliacoesAceitasPorUsuario(Long idUsuario) {
		
		List<Contabilizacao> listaDeContabilizacao = contabilizacaoDao
				.buscaPorUsuarioAno(idUsuario, 0);
		
		int contador = 0;
		for (Contabilizacao c: listaDeContabilizacao) {
			contador = contador + c.getQuantidadeAvaliacaoAceita();
		}
		return contador;
	}
	
	@Override
	public int countContabilizacaoGeralDeAvaliacoesRecusadasPorUsuario(Long idUsuario) {
		
		List<Contabilizacao> listaDeContabilizacao = contabilizacaoDao
				.buscaPorUsuarioAno(idUsuario, 0);
		
		int contador = 0;
		for (Contabilizacao c: listaDeContabilizacao) {
			contador = contador + c.getQuantidadeAvaliacaoRecusada();
		}
		return contador;
	}

}
