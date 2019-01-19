package com.topspin.boot.dao;

import java.util.List;

import com.topspin.boot.bean.FormUsuarioAmigo;
import com.topspin.boot.domain.Amigo;
import com.topspin.boot.domain.Usuario;

public interface AmigoDao {
	
	void save(Amigo amigo);
	
	void delete(Long id);
	
	Amigo buscaAmigo(FormUsuarioAmigo formUsuarioAmigo);
	
	List<Amigo> listaAmigos(Usuario usuario);

}
