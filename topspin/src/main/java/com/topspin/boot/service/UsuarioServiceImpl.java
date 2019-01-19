package com.topspin.boot.service;

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
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private AmigoDao amigoDao;
	
	@Override
	public void salva(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	public void atualiza(Usuario usuario) {
		usuarioDao.update(usuario);
	}

	@Override
	public void exclui(Long id) {
		usuarioDao.delete(id);
	}

	@Override @Transactional(readOnly = true)
	public Usuario buscaPorId(Long id) {
		return usuarioDao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Usuario> listaTodos() {
		return usuarioDao.findAll();
	}
	
	@Override @Transactional(readOnly = true)
	public Usuario buscaPorEmail(String email) {
		return usuarioDao.buscaPorEmail(email);
	}
	
	@Override @Transactional(readOnly = true)
	public List<Usuario> listaPorNome(String nome) {
		return usuarioDao.listaPorNome(nome);
	}
	
	@Override @Transactional(readOnly = true)
	public List<Usuario> listaPorEstado(String estado) {
		return usuarioDao.listaPorEstado(estado);
	}

	@Override
	public List<Usuario> listaPorFiltro(Usuario usuario) {
		return usuarioDao.listaPorFiltro(usuario);
	}
	
	@Override
	public List<Usuario> listaPorFiltroComFlagAmigo(Usuario usuario) {
		List<Usuario> lista = usuarioDao.listaPorFiltro(usuario);
		for (Usuario u: lista) {
			FormUsuarioAmigo formUsuarioAmigo = new FormUsuarioAmigo(usuario.getId(), u.getId());
			Amigo amigo = amigoDao.buscaAmigo(formUsuarioAmigo);
			if (amigo != null) {
				u.setAmigo(true);
			}else {
				u.setAmigo(false);
			}
		}
		return lista;
	}
	
	@Override
	public Usuario desativa(Long id) {
		Usuario u = buscaPorId(id);
		u.setStatus("D");
		return u;
	}

}
