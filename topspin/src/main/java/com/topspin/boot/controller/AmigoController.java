package com.topspin.boot.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topspin.boot.bean.FormUsuarioAmigo;
import com.topspin.boot.domain.Usuario;
import com.topspin.boot.service.AmigoService;

@CrossOrigin
@RestController
@RequestMapping(value="/amigos", produces=MediaType.APPLICATION_JSON_VALUE)
public class AmigoController {
	
	@Autowired
	private AmigoService amigoService;

	@PostMapping(value="/add")
	public ResponseEntity<?> adiciona(@RequestBody FormUsuarioAmigo formUsuarioAmigo) {
		amigoService.salva(formUsuarioAmigo);
	    return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
  	
  	@DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Void> remove(@RequestBody Long id){
      	amigoService.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  	
  	@PostMapping(value="/remove")
    public ResponseEntity<Void> remove(@RequestBody FormUsuarioAmigo formUsuarioAmigo){
      	amigoService.remove(formUsuarioAmigo);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  	
  	@GetMapping(value="/{id}")
    public ResponseEntity<List<Usuario>> amigosPorUsuario(@PathVariable long id) {
    	List<Usuario> listaAmigos = this.amigoService.listaAmigos(id);
        return new ResponseEntity<List<Usuario>>(listaAmigos, HttpStatus.OK);	
    }

}
