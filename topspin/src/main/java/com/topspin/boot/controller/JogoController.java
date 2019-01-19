package com.topspin.boot.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.topspin.boot.bean.FormJogo;
import com.topspin.boot.domain.Jogo;
import com.topspin.boot.service.JogoService;

@CrossOrigin()
@RestController
@RequestMapping(value="/jogos", produces=MediaType.APPLICATION_JSON_VALUE)
public class JogoController {
	
	@Autowired
	private JogoService jogoService;
	
	@GetMapping(value="/usuario/{id}")
	public ResponseEntity<List<Jogo>> getJogosDoUsuario(@PathVariable("id") Long id) {
		List<Jogo> listaDeJogos = jogoService.listaPorUsuario(id);
		return new ResponseEntity<List<Jogo>>(listaDeJogos, HttpStatus.OK);	
	}

	@PostMapping(value="/add")
    public ResponseEntity<Void> adiciona(@RequestBody FormJogo formJogo){
		jogoService.salva(formJogo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@GetMapping(value="/usuario/{id}/ultimosJogos/{qtd}")
	public ResponseEntity<FormJogo> getUltimosJogosDoUsuario(@PathVariable("id") Long id,
														   @PathVariable("qtd") int qtd) {
		FormJogo fj = jogoService.buscaUltimosJogosPorUsuario(id, qtd);
		return new ResponseEntity<FormJogo>(fj, HttpStatus.OK);	
	}

}
