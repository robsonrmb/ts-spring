package com.topspin.boot.service;

import java.util.List;

import com.topspin.boot.bean.FormConvite;
import com.topspin.boot.domain.Convite;

public interface ConviteService {

	void salva(FormConvite formConvite);
	
	void atualiza(Convite convite);
	
	void exclui(Long id);
	
	Convite buscaPorId(Long id);
	
	List<Convite> listaTodos();
	
	List<Convite> listaPorUsuarioEStatus(Convite convite);
	
	List<Convite> listaPorConvidadoEStatus(Convite convite);

	List<Convite> listaPorConvidadoENaoPendentes(Convite convite);

	void aceita(Convite convite);

	void recusa(Convite convite);
	
	int countPorConvidadoEPendentes(Convite convite);
	
}
