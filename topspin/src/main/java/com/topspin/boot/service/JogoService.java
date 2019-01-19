package com.topspin.boot.service;

import java.util.List;

import com.topspin.boot.bean.FormJogo;
import com.topspin.boot.domain.Jogo;

public interface JogoService {

	void salva(FormJogo formJogo);
	
	void atualiza(Jogo jogo);
	
	void exclui(Long id);
	
	Jogo buscaPorId(Long id);
	
	List<Jogo> listaTodos();
	
	List<Jogo> listaPorUsuario(Long id);

	FormJogo buscaUltimosJogosPorUsuario(Long id, int qtd);
	
}
