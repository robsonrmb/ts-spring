package com.topspin.boot.service;

import com.topspin.boot.bean.FormCadastroLogin;
import com.topspin.boot.domain.Acesso;

public interface AcessoService {

	void salva(FormCadastroLogin formCadastroLogin);
	
	void atualiza(Acesso acesso);
	
	void exclui(Long id);
	
	Acesso buscaPorId(Long id);
	
	boolean isExisteUsuario(Acesso acesso);
	
}
