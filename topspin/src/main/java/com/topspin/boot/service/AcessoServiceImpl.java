package com.topspin.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topspin.boot.bean.FormCadastroLogin;
import com.topspin.boot.dao.AcessoDao;
import com.topspin.boot.dao.UsuarioDao;
import com.topspin.boot.domain.Acesso;
import com.topspin.boot.domain.Usuario;

@Service @Transactional(readOnly = false)
public class AcessoServiceImpl implements AcessoService {

	@Autowired
	private AcessoDao acessoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public void salva(FormCadastroLogin formCadastroLogin) {
		
		Acesso acesso = new Acesso(formCadastroLogin.getEmail(), formCadastroLogin.getSenha());
		acessoDao.save(acesso);
		
		Usuario usuario = new Usuario(formCadastroLogin.getNome(), formCadastroLogin.getEmail(), formCadastroLogin.getEstado(), formCadastroLogin.getSexo(), "A");
		usuarioDao.save(usuario);
	}

	@Override
	public void atualiza(Acesso usuario) {
		acessoDao.update(usuario);
	}

	@Override
	public void exclui(Long id) {
		acessoDao.delete(id);
	}

	@Override @Transactional(readOnly = true)
	public Acesso buscaPorId(Long id) {
		return acessoDao.findById(id);
	}

	@Override
	public boolean isExisteUsuario(Acesso acesso) {
		return acessoDao.isExisteUsuario(acesso);
	}

}
