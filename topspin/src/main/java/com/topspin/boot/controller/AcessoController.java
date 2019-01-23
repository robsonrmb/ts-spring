package com.topspin.boot.controller;

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

import com.topspin.boot.bean.FormCadastroLogin;
import com.topspin.boot.domain.Acesso;
import com.topspin.boot.service.AcessoService;

@CrossOrigin
@RestController
@RequestMapping(value="/acesso", produces=MediaType.APPLICATION_JSON_VALUE)
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;

	@PostMapping(value="/add")
    public ResponseEntity<Boolean> adiciona(@RequestBody FormCadastroLogin formCadastroLogin) {
		acessoService.salva(formCadastroLogin);
        return new ResponseEntity<Boolean>(HttpStatus.CREATED);
    }
	
	@PutMapping(value="/update")
    public ResponseEntity<Boolean> atualiza(@RequestBody Acesso acesso){
    	acessoService.atualiza(acesso);
        return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
    }
    
    @PostMapping(value="/existe")
    public ResponseEntity<Boolean> isExisteUsuario(@RequestBody Acesso acesso) {
    	
    	boolean isAcesso = this.acessoService.isExisteUsuario(acesso);
        return new ResponseEntity<Boolean>(isAcesso, HttpStatus.OK);	
    }
    
    //SEM NECESSIDADE - AVALIAR
    @GetMapping(value="/{id}")
	public ResponseEntity<Acesso> getUsuario(@PathVariable("id") Long id) {
		
    	Acesso acesso = acessoService.buscaPorId(id);
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);	
	}
    
    //APENAS ADMINISTRATIVO
    @DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id){
    	acessoService.exclui(id);
        return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
    }
	
}
