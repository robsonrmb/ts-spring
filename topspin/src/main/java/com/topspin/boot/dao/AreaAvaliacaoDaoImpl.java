package com.topspin.boot.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.topspin.boot.domain.AreaAvaliacao;
import com.topspin.boot.domain.TipoAvaliacao;

@Repository
public class AreaAvaliacaoDaoImpl extends AbstractDao<TipoAvaliacao, Long> implements AreaAvaliacaoDao {

	@Override
	public List<AreaAvaliacao> listaAreasAvaliacaoCompleto() {
		String query = "SELECT aa FROM AreaAvaliacao aa order by aa.id";
		TypedQuery<AreaAvaliacao> q = getEntityManager().createQuery(query, AreaAvaliacao.class); 
		return q.getResultList();
	}

}
