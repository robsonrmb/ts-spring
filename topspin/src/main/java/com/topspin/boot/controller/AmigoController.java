package com.topspin.boot.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.topspin.boot.bean.FormUsuarioAmigo;
import com.topspin.boot.domain.Usuario;
import com.topspin.boot.exception.ApiNegocioRuntimeException;
import com.topspin.boot.service.AmigoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API de amigos.")
@CrossOrigin
@RestController
@RequestMapping(value="/amigos", produces=MediaType.APPLICATION_JSON_VALUE)
public class AmigoController {
	
	@Autowired
	private AmigoServiceImpl amigoService;

	@ApiOperation(value="Lista os amigos de um usuário.")
	@GetMapping(value="/{id}")
    public ResponseEntity<List<Usuario>> amigosPorUsuario(@PathVariable long id) {
		if (id == 0) {
			throw new ApiNegocioRuntimeException("Usuário não informado!!!", HttpStatus.BAD_REQUEST, "Informe um usuário para realizar a pesquisa.");
  		}
		List<Usuario> listaAmigos = this.amigoService.listaAmigos(id);
        return new ResponseEntity<List<Usuario>>(listaAmigos, HttpStatus.OK);	
    }
	
	@ApiOperation(value="Adiciona um amigo para um usuário.")
	@PostMapping(value="/add")
	public ResponseEntity<?> adiciona(@RequestBody @Valid FormUsuarioAmigo formUsuarioAmigo) {
		amigoService.salva(formUsuarioAmigo);
	    return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
  	
	@ApiOperation(value="Remove um amigo de um usuário.")
	@PostMapping(value="/remove")
    public ResponseEntity<Void> remove(@RequestBody FormUsuarioAmigo formUsuarioAmigo){
      	amigoService.remove(formUsuarioAmigo);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  	
	/*
     * ================================================
     * OS MÉTODOS ABAIXO NÃO ESTÃO SENDO USADOS.
     * FORAM FEITOS PARA FINS DE TESTES E APRENDIZADOS.
     * ================================================
     */
	/*
	@ApiOperation(value="Remove um amigo conforme identificador (id)")
  	@DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Void> remove(@RequestBody Long id){
      	amigoService.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
	*/
}
