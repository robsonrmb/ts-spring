package com.topspin.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topspin.boot.bean.FormAvaliacao;
import com.topspin.boot.bean.Quantidade;
import com.topspin.boot.domain.Avaliacao;
import com.topspin.boot.domain.Usuario;
import com.topspin.boot.service.AvaliacaoService;

@CrossOrigin()
@RestController
@RequestMapping(value="/avaliacoes", produces=MediaType.APPLICATION_JSON_VALUE)
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@PostMapping(value="/add")
    public ResponseEntity<Void> adiciona(@RequestBody FormAvaliacao formAvaliacao){
		avaliacaoService.salva(formAvaliacao);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@PutMapping(value="/aceita")
    public ResponseEntity<Void> aceitaAvaliacao(@RequestBody FormAvaliacao formAvaliacao){
      	avaliacaoService.atualiza(formAvaliacao, "A");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
	
	@PutMapping(value="/recusa")
    public ResponseEntity<Void> recusaAvaliacao(@RequestBody FormAvaliacao formAvaliacao){
      	avaliacaoService.atualiza(formAvaliacao, "R");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  	
  	@DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
  		//verificar se pode excluir: somente o usuário da avaliacao e somente se estiver pendente
      	avaliacaoService.exclui(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  	
  	@GetMapping(value="/recebidas/{idUsuario}/status/{status}")
	public ResponseEntity<List<FormAvaliacao>> getAvaliacoesDoAvaliado(@PathVariable("idUsuario") Long idUsuario,
																  	   @PathVariable("status") String status) {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliado(usuario);
		avaliacao.setStatus(status);
		
		List<Avaliacao> listaDeAvaliacoes = avaliacaoService.listaPorAvaliadoEStatus(avaliacao);
		List<FormAvaliacao> listaFA = converteParaFormAvaliacao(listaDeAvaliacoes);
		return new ResponseEntity<List<FormAvaliacao>>(listaFA, HttpStatus.OK);
	}
  	
  	@GetMapping(value="/recebidas/{idUsuario}/countPendentes")
	public ResponseEntity<Quantidade> countAvaliacoesDoAvaliadoEPendentes(@PathVariable("idUsuario") Long idUsuario) {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliado(usuario);
		
		int quantidade = avaliacaoService.countPorAvaliadoEPendente(avaliacao);
		Quantidade q = new Quantidade(quantidade);
		return new ResponseEntity<Quantidade>(q, HttpStatus.OK);
	}
    
    private List<FormAvaliacao> converteParaFormAvaliacao(List<Avaliacao> listaDeAvaliacoes) {
		List<FormAvaliacao> listaFA = new ArrayList<FormAvaliacao>();
		for(Avaliacao a: listaDeAvaliacoes) {
			FormAvaliacao fa = new FormAvaliacao();
			fa.setId(a.getId());
			fa.setIdUsuario(a.getAvaliador().getId());
			fa.setIdAvaliado(a.getAvaliado().getId());
			fa.setData(a.getData());
			fa.setStatus(a.getStatus());
			
			fa.setNomeUsuario(a.getAvaliador().getNome());
			fa.setNomeAvaliado(a.getAvaliado().getNome());
			
			listaFA.add(fa);
		}
		return listaFA;
	}

}
