package com.topspin.boot.service;

import java.util.List;

import com.topspin.boot.bean.FormUsuarioAmigo;
import com.topspin.boot.domain.Usuario;

public interface AmigoService {

	void salva(FormUsuarioAmigo formUsuarioAmigo);
	
	void remove(Long id);
	
	void remove(FormUsuarioAmigo formUsuarioAmigo);
	
	List<Usuario> listaAmigos(Long id);
	
}
