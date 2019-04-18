package com.topspin.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topspin.boot.bean.FormCadastroLogin;
import com.topspin.boot.domain.Acesso;
import com.topspin.boot.error.ResourceBadRequestException;
import com.topspin.boot.service.AcessoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API de acesso/login.")
@CrossOrigin
@RestController
@RequestMapping(value="/acesso", produces=MediaType.APPLICATION_JSON_VALUE)
public class AcessoController {
	
	@Autowired
	private AcessoServiceImpl acessoService;

	@ApiOperation(value="Adiciona um usuário ao sistema.", 
				  notes="Operação em uso.")
	@PostMapping(value="/add")
    public ResponseEntity<Boolean> adiciona(@RequestBody FormCadastroLogin formCadastroLogin) {
		acessoService.salva(formCadastroLogin);
        return new ResponseEntity<Boolean>(HttpStatus.CREATED);
    }
	
	@ApiOperation(value="Consulta se usuário está cadastrado no sistema.", 
				  notes="Operação em uso.")
	@PostMapping(value="/existe")
    public ResponseEntity<Boolean> isExisteUsuario(@RequestBody Acesso acesso) {
    	verificaSeUsuarioExiste(acesso);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);	
    }
	
	private void verificaSeUsuarioExiste(Acesso acesso) {
		if (!this.acessoService.isExisteUsuario(acesso)) {
    		throw new ResourceBadRequestException("Dados inválidos!!!");
    	}
	}
    
	/*
     * ================================================
     * OS MÉTODOS ABAIXO NÃO ESTÃO SENDO USADOS.
     * FORAM FEITOS PARA FINS DE TESTES E APRENDIZADOS.
     * ================================================
     */
	/*
	@ApiOperation(value="Altera os dados de login de um usuário conforme dados do formulário.", 
				  notes="Operação não usada.")
    @PutMapping(value="/update")
    public ResponseEntity<Boolean> atualiza(@RequestBody Acesso acesso){
		verificaSeUsuarioExiste(acesso);
    	acessoService.atualiza(acesso);
        return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
    }
    
	@ApiOperation(value="Consulta um usuário apartir do identificador (id).", 
			  	  notes="Operação não usada.")
    @GetMapping(value="/{id}")
	public ResponseEntity<Acesso> getUsuario(@PathVariable("id") Long id) {
		Acesso acesso = acessoService.buscaPorId(id);
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);	
	}
    
	@ApiOperation(value="Remove um usuário apartir do identificador (id).", 
			  	  notes="Operação não usada. Operação administrativa.")
    @DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id){
    	acessoService.exclui(id);
        return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
    }
	*/
}
