package com.topspin.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topspin.boot.bean.FormUsuarioAmigo;
import com.topspin.boot.dao.AmigoDao;
import com.topspin.boot.dao.UsuarioDao;
import com.topspin.boot.domain.Amigo;
import com.topspin.boot.domain.Usuario;

@Service @Transactional(readOnly = false)
public class AmigoServiceImpl implements AmigoService {

	@Autowired
	private AmigoDao amigoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public void salva(FormUsuarioAmigo formUsuarioAmigo) {
		Usuario usuario = usuarioDao.findById(formUsuarioAmigo.getIdUsuario());
		Usuario uAmigo = usuarioDao.findById(formUsuarioAmigo.getIdAmigo());
		
		Amigo amigo = new Amigo();
		amigo.setUsuario(usuario);
		amigo.setAmigo(uAmigo);
		
		amigoDao.save(amigo);
	}

	@Override
	public void remove(Long id) {
		amigoDao.delete(id);
	}
	
	@Override
	public void remove(FormUsuarioAmigo formUsuarioAmigo) {
		Amigo amigo = amigoDao.buscaAmigo(formUsuarioAmigo);
		amigoDao.delete(amigo.getId());
	}

	@Override 
	@Transactional(readOnly = true)
	public List<Usuario> listaAmigos(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Amigo> listaAmigos = amigoDao.listaAmigos(usuario);
		for (Amigo amigo: listaAmigos) {
			Usuario uAmigo = amigo.getAmigo();
			listaUsuarios.add(uAmigo);
		}
		return listaUsuarios;
	}

}
