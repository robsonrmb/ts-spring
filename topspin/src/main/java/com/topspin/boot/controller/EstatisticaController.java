package com.topspin.boot.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topspin.boot.bean.FormRespQuantidade;
import com.topspin.boot.domain.TipoEstatistica;
import com.topspin.boot.domain.TipoRespostaEstatistica;
import com.topspin.boot.service.ContabilizacaoService;
import com.topspin.boot.service.EstatisticaService;
import com.topspin.boot.service.TipoEstatisticaService;

@CrossOrigin()
@RestController
@RequestMapping(value="/estatisticas", produces=MediaType.APPLICATION_JSON_VALUE)
public class EstatisticaController {
	
	@Autowired
	private EstatisticaService estatisticaService;
	
	@Autowired
	private TipoEstatisticaService tipoEstatisticaService;
	
	@Autowired
	private ContabilizacaoService contabilizacaoService;
	
	@GetMapping(value="usuario/{id}/vitoriasederrotas")
	public ResponseEntity<FormRespQuantidade> getQuantidadeDeVitorias(@PathVariable("id") Long id) {
		
		int qtdVitoria = estatisticaService.buscaEstatistica(id, "VITORIA");
		int qtdDerrota = estatisticaService.buscaEstatistica(id, "DERROTA");
		
		FormRespQuantidade frq = new FormRespQuantidade();
		frq.setValor1(qtdVitoria);
		frq.setValor2(qtdDerrota);
		
		return new ResponseEntity<FormRespQuantidade>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="usuario/{id}/tiebreaks")
	public ResponseEntity<FormRespQuantidade> getQuantidadeDeTieBreaksVencidos(@PathVariable("id") Long id) {
		
		int qtdVencidos = estatisticaService.buscaEstatistica(id, "TIEBREAKVENCIDO");
		int qtdPerdidos = estatisticaService.buscaEstatistica(id, "TIEBREAKPERDIDO");
		
		FormRespQuantidade frq = new FormRespQuantidade();
		frq.setValor1(qtdVencidos);
		frq.setValor2(qtdPerdidos);
		
		return new ResponseEntity<FormRespQuantidade>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="usuario/{id}/tipoEstatistica/{tipo}")
	public ResponseEntity<FormRespQuantidade> getQuantidadeDeSaque(@PathVariable("id") Long id,
																   @PathVariable("tipo") String tipo) {
		
		int qtdAvaliacoesAceita = contabilizacaoService.countContabilizacaoGeralDeAvaliacoesAceitasPorUsuario(id);
		
		if (qtdAvaliacoesAceita <= 3) {
			//TODO retornar uma exceção? Status? Tratar a excessão no frontEnd. 
		}
		
		long valores[] = new long[12];
		
		TipoEstatistica te = tipoEstatisticaService.buscaPorNome(tipo);
		Iterator<TipoRespostaEstatistica> i = te.getTipoRespostas().iterator();
		int contador = 0;
		while (i.hasNext()) {
			long idTipoResposta = i.next().getId();
			
			valores[contador] = estatisticaService.buscaEstatistica(id, te.getId(), idTipoResposta);
			contador++;
		}
		
		FormRespQuantidade frq = new FormRespQuantidade();
		frq.setValor1(Integer.parseInt(valores[0]+""));
		frq.setValor2(Integer.parseInt(valores[1]+""));
		frq.setValor3(Integer.parseInt(valores[2]+""));
		frq.setValor4(Integer.parseInt(valores[3]+""));
		
		return new ResponseEntity<FormRespQuantidade>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="usuario/{id}/qtdAvaliacoesAceitas")
	public ResponseEntity<FormRespQuantidade> getQuantidadeDeAvaliacoesAceitas(@PathVariable("id") Long id) {
		
		int qtdAvaliacoesAceitas = contabilizacaoService.countContabilizacaoGeralDeAvaliacoesAceitasPorUsuario(id);
		
		FormRespQuantidade frq = new FormRespQuantidade();
		frq.setValor1(qtdAvaliacoesAceitas);
		
		return new ResponseEntity<FormRespQuantidade>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="usuario/{id}/qtdAvaliacoesRecusadas")
	public ResponseEntity<FormRespQuantidade> getQuantidadeDeAvaliacoesRecusadas(@PathVariable("id") Long id) {
		
		int qtdAvaliacoesRecusadas = contabilizacaoService.countContabilizacaoGeralDeAvaliacoesRecusadasPorUsuario(id);
		
		FormRespQuantidade frq = new FormRespQuantidade();
		frq.setValor1(qtdAvaliacoesRecusadas);
		
		return new ResponseEntity<FormRespQuantidade>(frq, HttpStatus.OK);
	}
	
}
