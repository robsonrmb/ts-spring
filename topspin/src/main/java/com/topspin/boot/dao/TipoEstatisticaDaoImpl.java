package com.topspin.boot.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.topspin.boot.domain.TipoEstatistica;

@Repository
public class TipoEstatisticaDaoImpl extends AbstractDao<TipoEstatistica, Long> implements TipoEstatisticaDao {

	@Override
	public TipoEstatistica buscaPorNome(String nome) {
		String query = "SELECT te FROM TipoEstatistica te where 1=1 and te.nome = :nome";
		TypedQuery<TipoEstatistica> q = getEntityManager().createQuery(query, TipoEstatistica.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}

}
