package com.topspin.boot.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.topspin.boot.bean.FormUsuarioAmigo;
import com.topspin.boot.domain.Amigo;
import com.topspin.boot.domain.Usuario;

@Repository
public class AmigoDaoImpl extends AbstractDao<Amigo, Long> {

	public Amigo buscaAmigo(FormUsuarioAmigo formUsuarioAmigo) {
		TypedQuery<Amigo> q = getEntityManager().createNamedQuery("busca.porUsuarioEAmigo", Amigo.class); 
		q.setParameter("idUsuario", formUsuarioAmigo.getIdUsuario());
		q.setParameter("idAmigo", formUsuarioAmigo.getIdAmigo());
		List<Amigo> l = q.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}else {
			return null;
		}
	}
	
	public List<Amigo> listaAmigos(Usuario usuario) {
		TypedQuery<Amigo> q = getEntityManager().createNamedQuery("lista.porUsuario", Amigo.class); 
		q.setParameter("id", usuario.getId());
		return q.getResultList();
	}

}
