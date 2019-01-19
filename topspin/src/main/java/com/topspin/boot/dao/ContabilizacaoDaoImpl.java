package com.topspin.boot.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.topspin.boot.domain.Contabilizacao;

@Repository
public class ContabilizacaoDaoImpl extends AbstractDao<Contabilizacao, Long> implements ContabilizacaoDao {

	@Override
	public List<Contabilizacao> buscaPorUsuarioAno(long idUsuario, int ano) {
		String query = "SELECT c FROM Contabilizacao c where 1=1";
		if (idUsuario != 0) {
			query = query + " and c.usuario.id = :idUsuario";
		}
		if (ano != 0) {
			query = query + " and c.ano = :ano";
		}
		TypedQuery<Contabilizacao> q = getEntityManager().createQuery(query, Contabilizacao.class); 
		if (idUsuario != 0) {
			q.setParameter("idUsuario", idUsuario);
		}
		if (ano != 0) {
			q.setParameter("ano", ano);
		}
		return q.getResultList();
	}

}
