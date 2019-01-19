package com.topspin.boot.service;

import java.util.List;

import com.topspin.boot.domain.Usuario;

public interface UsuarioService {

	void salva(Usuario usuario);
	
	void atualiza(Usuario usuario);
	
	void exclui(Long id);
	
	Usuario buscaPorId(Long id);
	
	List<Usuario> listaTodos();
	
	Usuario buscaPorEmail(String email);
	
	List<Usuario> listaPorNome(String nome);
	
	List<Usuario> listaPorEstado(String estado);

	List<Usuario> listaPorFiltro(Usuario usuario);
	
	List<Usuario> listaPorFiltroComFlagAmigo(Usuario usuario);
	
	Usuario desativa(Long id);
	
}
