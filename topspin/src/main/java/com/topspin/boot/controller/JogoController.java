package com.topspin.boot.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.topspin.boot.bean.FormJogo;
import com.topspin.boot.domain.Jogo;
import com.topspin.boot.service.JogoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API de jogos.")
@CrossOrigin()
@RestController
@RequestMapping(value="/jogos", produces=MediaType.APPLICATION_JSON_VALUE)
public class JogoController {
	
	@Autowired
	private JogoServiceImpl jogoService;
	
	@ApiOperation(value="Adiciona o resultado do último jogo do usuário.")
	@PostMapping(value="/add")
    public ResponseEntity<Void> adiciona(@RequestBody FormJogo formJogo){
		Jogo jogo = jogoService.salva(formJogo);
		return new ResponseEntity<>(HttpStatus.CREATED);
		/*
		 * Uma inserção deve retornar no Header da requisição o recurso disponível deste novo objeto criado. (uri)
		 * ResponseEntity.created referente a inclusão.
		 * 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jogo.getId()).toUri();
		return ResponseEntity.created(uri).build();
		*/
    }
	
	@ApiOperation(value="Lista os últimos jogos de um usuário.")
	@GetMapping(value="/ultimos-jogos")
	public ResponseEntity<FormJogo> getUltimosJogosDoUsuario(@RequestParam(name="usuario", required=true) Long idUsuario,
															 @RequestParam(name="qtd", required=true) int qtd) {
		
		if (qtd == 0) {
			qtd = 5;
		}
		FormJogo fj = jogoService.buscaUltimosJogosPorUsuario(idUsuario, qtd);
		return new ResponseEntity<FormJogo>(fj, HttpStatus.OK);	
	}
	
	@ApiOperation(value="Lista todos os jogos de um usuário.")
	@GetMapping(value="/usuario/{id}")
	public ResponseEntity<List<FormJogo>> getJogosDoUsuario(@PathVariable("id") Long id) {
		List<Jogo> listaDeJogos = jogoService.listaPorUsuario(id);
		List<FormJogo> lista = converteJogosParaFormJogo(listaDeJogos);
		return new ResponseEntity<List<FormJogo>>(lista, HttpStatus.OK);	
	}

	private List<FormJogo> converteJogosParaFormJogo(List<Jogo> listaDeJogos) {
		List<FormJogo> lista_fj = new ArrayList<FormJogo>();
		for (Jogo jogo: listaDeJogos) {
			FormJogo fj = new FormJogo();
			fj.setId(jogo.getId());
			fj.setIdUsuario(jogo.getUsuario().getId());
			
			//No cadastro do jogo não está sendo informado o adversário.
			//fj.setIdAdversario(jogo.getAdversario().getId());
			
			fj.setData(jogo.getData());
			fj.setTipo(jogo.getTipo());
			fj.setResultado(jogo.getResultado());
			fj.setPlacar(jogo.getPlacar());
			fj.setQtdTieVencidos(jogo.getQtdTieVencidos());
			fj.setQtdTiePerdidos(jogo.getQtdTiePerdidos());
			fj.setDataJogoFormatada(jogo.getDataJogoFormatada());
			
			lista_fj.add(fj);
		}
		return lista_fj;
	}

}
