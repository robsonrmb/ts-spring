package com.topspin.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topspin.boot.domain.AreaAvaliacao;
import com.topspin.boot.service.AreaAvaliacaoService;

import io.swagger.annotations.Api;

@Api(value="API de área de avaliações.")
@CrossOrigin()
@RestController
@RequestMapping(value="/area-avaliacoes", produces=MediaType.APPLICATION_JSON_VALUE)
public class AreaAvaliacaoController {
	
	@Autowired
	private AreaAvaliacaoService areaAvaliacaoService;
	
	@GetMapping(value="/ativas")
	public ResponseEntity<List<AreaAvaliacao>> getAreaAvaliacoes() {
		List<AreaAvaliacao> listaDeAreasAvaliacoes = areaAvaliacaoService.listaAreasAvaliacaoCompleto();
		return new ResponseEntity<List<AreaAvaliacao>>(listaDeAreasAvaliacoes, HttpStatus.OK);
	}

}
