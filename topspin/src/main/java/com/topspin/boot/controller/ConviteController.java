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

import com.topspin.boot.bean.FormConvite;
import com.topspin.boot.bean.Quantidade;
import com.topspin.boot.domain.Convite;
import com.topspin.boot.domain.Usuario;
import com.topspin.boot.service.ConviteServiceImpl;

@CrossOrigin()
@RestController
@RequestMapping(value="/convites", produces=MediaType.APPLICATION_JSON_VALUE)
public class ConviteController {
	
	@Autowired
	private ConviteServiceImpl conviteService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Convite> getUsuario(@PathVariable("id") Long id) {
		Convite convite = conviteService.buscaPorId(id);
		return new ResponseEntity<Convite>(convite, HttpStatus.OK);	
	}

	@GetMapping(value="/usuario/{idUsuario}")
	public ResponseEntity<List<FormConvite>> getConvitesDoUsuario(@PathVariable("idUsuario") Long idUsuario) {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		
		Convite convite = new Convite();
		convite.setUsuario(usuario);
		
		List<Convite> listaDeConvites = conviteService.listaPorUsuarioEStatus(convite);
		List<FormConvite> listaFC = converteParaFormConvite(listaDeConvites);
		return new ResponseEntity<List<FormConvite>>(listaFC, HttpStatus.OK);
	}

	@GetMapping(value="/usuario/{idUsuario}/status/{status}")
	public ResponseEntity<List<FormConvite>> getConvitesDoUsuario(@PathVariable("idUsuario") Long idUsuario,
																  @PathVariable("status") String status) {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		
		Convite convite = new Convite();
		convite.setUsuario(usuario);
		convite.setStatus(status);
		
		List<Convite> listaDeConvites = conviteService.listaPorUsuarioEStatus(convite);
		List<FormConvite> listaFC = converteParaFormConvite(listaDeConvites);
		return new ResponseEntity<List<FormConvite>>(listaFC, HttpStatus.OK);
	}
	
	@GetMapping(value="/convidado/{idConvidado}")
	public ResponseEntity<List<FormConvite>> getConvitesDoConvidado(@PathVariable("idConvidado") Long idConvidado) {
		Usuario convidado = new Usuario();
		convidado.setId(idConvidado);
		
		Convite convite = new Convite();
		convite.setConvidado(convidado);
		
		List<Convite> listaDeConvites = conviteService.listaPorConvidadoEStatus(convite);
		List<FormConvite> listaFC = converteParaFormConvite(listaDeConvites);
		return new ResponseEntity<List<FormConvite>>(listaFC, HttpStatus.OK);
	}
	
	@GetMapping(value="/convidado/{idConvidado}/status/{status}")
	public ResponseEntity<List<FormConvite>> getConvitesDoConvidado(@PathVariable("idConvidado") Long idConvidado,
																	@PathVariable("status") String status) {
		Usuario convidado = new Usuario();
		convidado.setId(idConvidado);
		
		Convite convite = new Convite();
		convite.setConvidado(convidado);
		convite.setStatus(status);
		
		List<Convite> listaDeConvites = conviteService.listaPorConvidadoEStatus(convite);
		List<FormConvite> listaFC = converteParaFormConvite(listaDeConvites);
		return new ResponseEntity<List<FormConvite>>(listaFC, HttpStatus.OK);
	}
	
	@GetMapping(value="/convidado/{idConvidado}/naoPendentes")
	public ResponseEntity<List<FormConvite>> getConvitesNaoPendentesDoConvidado(@PathVariable("idConvidado") Long idConvidado) {
		Usuario convidado = new Usuario();
		convidado.setId(idConvidado);
		
		Convite convite = new Convite();
		convite.setConvidado(convidado);
		
		List<Convite> listaDeConvites = conviteService.listaPorConvidadoENaoPendentes(convite);
		List<FormConvite> listaFC = converteParaFormConvite(listaDeConvites);
		return new ResponseEntity<List<FormConvite>>(listaFC, HttpStatus.OK);
	}
	
	@GetMapping(value="/convidado/{idConvidado}/countPendentes")
	public ResponseEntity<Quantidade> getCountPendentesDoConvidado(@PathVariable("idConvidado") Long idConvidado) {
		Usuario convidado = new Usuario();
		convidado.setId(idConvidado);
		
		Convite convite = new Convite();
		convite.setConvidado(convidado);
		
		int quantidade = conviteService.countPorConvidadoEPendentes(convite);
		Quantidade q = new Quantidade(quantidade);
		return new ResponseEntity<Quantidade>(q, HttpStatus.OK);
	}
	
	@PostMapping(value="/add")
    public ResponseEntity<Void> adiciona(@RequestBody FormConvite formConvite){
		
		conviteService.salva(formConvite);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@PutMapping(value="/update")
    public ResponseEntity<Void> atualiza(@RequestBody Convite convite){
      	conviteService.atualiza(convite);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
	
	@PutMapping(value="/aceita")
    public ResponseEntity<Void> aceita(@RequestBody Convite convite){
      	conviteService.aceita(convite);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
	
	@PutMapping(value="/recusa")
    public ResponseEntity<Void> recusa(@RequestBody Convite convite){
      	conviteService.recusa(convite);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  	
  	@DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
  		//verificar se pode excluir: somente o usuário do convite e somente se estiver pendente
      	conviteService.exclui(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    /*
    @GetMapping(value="/filter")
    public ResponseEntity<List<Usuario>> listaPorFiltro(@RequestParam(name="nome", required=false) String nome,
    													@RequestParam(name="email", required=false) String email,
    													@RequestParam(name="ondeJoga", required=false) String ondeJoga,
    													@RequestParam(name="tipo", required=false) String tipo,
    													@RequestParam(name="nivel", required=false) String nivel,
    													@RequestParam(name="estado", required=false) String estado) {
    	
    	//Usuario usuario = new Usuario(nome, email, ondeJoga, tipo, nivel, estado);
       
        List<Usuario> listaUsuarios = this.usuarioService.listaPorFiltros(usuario);
        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);	
    }
    */
  	
  	private List<FormConvite> converteParaFormConvite(List<Convite> listaDeConvites) {
		List<FormConvite> listaFC = new ArrayList<FormConvite>();
		for(Convite c: listaDeConvites) {
			FormConvite fc = new FormConvite();
			fc.setId(c.getId());
			fc.setIdUsuario(c.getUsuario().getId());
			fc.setIdConvidado(c.getConvidado().getId());
			fc.setData(c.getData());
			fc.setPeriodo(c.getPeriodo());
			fc.setLocalJogo(c.getLocalJogo());
			fc.setDescricao(c.getDescricao());
			fc.setStatus(c.getStatus());
			
			fc.setNomeUsuario(c.getUsuario().getNome());
			fc.setNomeConvidado(c.getConvidado().getNome());
			
			listaFC.add(fc);
		}
		return listaFC;
	}

}
