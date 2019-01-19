package com.topspin.boot.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topspin.boot.bean.FormAvaliacao;
import com.topspin.boot.dao.AvaliacaoDao;
import com.topspin.boot.dao.AvaliacaoRespostasDao;
import com.topspin.boot.dao.TipoAvaliacaoDao;
import com.topspin.boot.dao.TipoRespostaAvaliacaoDao;
import com.topspin.boot.dao.UsuarioDao;
import com.topspin.boot.domain.Avaliacao;
import com.topspin.boot.domain.AvaliacaoRespostas;
import com.topspin.boot.domain.Contabilizacao;
import com.topspin.boot.domain.Estatistica;
import com.topspin.boot.domain.TipoAvaliacao;
import com.topspin.boot.domain.TipoEstatistica;
import com.topspin.boot.domain.TipoRespostaAvaliacao;
import com.topspin.boot.domain.TipoRespostaEstatistica;
import com.topspin.boot.domain.Usuario;

@Service @Transactional(readOnly = false)
public class AvaliacaoServiceImpl implements AvaliacaoService {

	@Autowired
	private AvaliacaoDao avaliacaoDao;
	
	@Autowired
	private AvaliacaoRespostasDao avaliacaoRespostasDao;
	
	@Autowired
	private TipoAvaliacaoDao tipoAvaliacaoDao;
	
	@Autowired
	private TipoRespostaAvaliacaoDao tipoRespostaAvaliacaoDao;
	
	@Autowired
	private EstatisticaService estatisticaService;
	
	@Autowired
	private TipoEstatisticaService tipoEstatisticaService;
	
	@Autowired
	private TipoRespostaEstatisticaService tipoRespostaEstatisticaService;
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private ContabilizacaoService contabilizacaoService;

	@Override
	public void salva(FormAvaliacao formAvaliacao) {
		
		Usuario usuario = usuarioDao.findById(formAvaliacao.getIdUsuario());
		Usuario avaliado = usuarioDao.findById(formAvaliacao.getIdAvaliado());
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliador(usuario);
		avaliacao.setAvaliado(avaliado);
		
		avaliacao.setData(new Date());
		avaliacao.setStatus("P");
		avaliacaoDao.save(avaliacao);
		
		TipoAvaliacao tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("SAQUE");
		TipoRespostaAvaliacao tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaSaque());
		AvaliacaoRespostas ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("FOREHAND");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaForehand());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("BACKHAND");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaBackhand());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("VOLEIO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaVoleio());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("SMASH");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaSmash());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("OFENSIVO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaOfensivo());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("DEFENSIVO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaDefensivo());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("TATICO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaTatico());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("COMPETITIVO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaCompetitivo());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("PREPARO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(formAvaliacao.getRespostaPreparo());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
	}

	@Override
	public void atualiza(FormAvaliacao formAvaliacao, String status) {
		Avaliacao avaliacao = avaliacaoDao.findById(formAvaliacao.getId());
		avaliacao.setStatus(status);
		
		if (status != null && status.equals("A")) {	
			
			Calendar dataAtual = Calendar.getInstance();
			
			//GRAVAR AS ESTATÍSTICAS
			gravaEstatistica("SAQUE", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("FOREHAND", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("BACKHAND", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("VOLEIO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("SMASH", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("OFENSIVO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("DEFENSIVO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("TATICO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("COMPETITIVO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("PREPARO", avaliacao, dataAtual.get(Calendar.YEAR));
			
		}
		//GRAVA A CONTABILIZACAO
		//TODO método depreciado
		int ano = avaliacao.getData().getYear();
		gravaContabilizacao(avaliacao.getAvaliado(), status, ano);
	}

	private void gravaContabilizacao(Usuario avaliado, String status, int ano) {
		Contabilizacao contabilizacao = new Contabilizacao();
		contabilizacao.setUsuario(avaliado);
		contabilizacao.setAno(ano);
		contabilizacaoService.salva(contabilizacao, status);
	}

	private void gravaEstatistica(String nome, Avaliacao avaliacao, int ano) {
		Estatistica estatistica = new Estatistica();
		estatistica.setUsuario(avaliacao.getAvaliado());
		estatistica.setAno(ano);
		
		TipoAvaliacao ta = tipoAvaliacaoDao.buscaPorNome(nome);
		long idAvaliacaoResposta = 0;
		for (AvaliacaoRespostas ar: avaliacao.getRespostas()) {
			if (ar.getIdTipoAvaliacao() == ta.getId()) {
				idAvaliacaoResposta = ar.getIdTipoResposta();
				break;
			}
		}
		TipoRespostaAvaliacao tra = tipoRespostaAvaliacaoDao.findById(idAvaliacaoResposta);
		
		TipoEstatistica te = tipoEstatisticaService.buscaPorNome(nome);
		TipoRespostaEstatistica tre = tipoRespostaEstatisticaService.buscaPorNome(tra.getNome());
		
		estatistica.setIdTipoEstatistica(te.getId());
		estatistica.setIdTipoResposta(tre.getId());
		estatistica.setQuantidade(1);
		estatisticaService.salva(estatistica);
	}

	@Override
	public void exclui(Long id) {
		avaliacaoDao.delete(id);
	}

	@Override
	public List<Avaliacao> listaPorAvaliadoEStatus(Avaliacao avaliacao) {
		return avaliacaoDao.listaPorAvaliadoEStatus(avaliacao);
	}
	
	@Override
	public int countPorAvaliadoEPendente(Avaliacao avaliacao) {
		return avaliacaoDao.countPorAvaliadoEPendente(avaliacao);
	}

}
