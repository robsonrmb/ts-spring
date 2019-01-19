package com.topspin.boot.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.topspin.boot.domain.TipoRespostaEstatistica;

@Repository
public class TipoRespostaEstatisticaDaoImpl extends AbstractDao<TipoRespostaEstatistica, Long> implements TipoRespostaEstatisticaDao {

	@Override
	public TipoRespostaEstatistica buscaPorNome(String nome) {
		String query = "SELECT tre FROM TipoRespostaEstatistica tre where 1=1 and tre.nome = :nome";
		TypedQuery<TipoRespostaEstatistica> q = getEntityManager().createQuery(query, TipoRespostaEstatistica.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}

}
