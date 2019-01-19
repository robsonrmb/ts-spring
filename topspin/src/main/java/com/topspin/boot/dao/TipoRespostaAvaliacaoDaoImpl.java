package com.topspin.boot.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.topspin.boot.domain.TipoRespostaAvaliacao;

@Repository
public class TipoRespostaAvaliacaoDaoImpl extends AbstractDao<TipoRespostaAvaliacao, Long> implements TipoRespostaAvaliacaoDao {

	@Override
	public TipoRespostaAvaliacao buscaPorNome(String nome) {
		String query = "SELECT tra FROM TipoRespostaAvaliacao tra where 1=1 and tra.nome = :nome";
		TypedQuery<TipoRespostaAvaliacao> q = getEntityManager().createQuery(query, TipoRespostaAvaliacao.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}

}
