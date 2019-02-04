package com.topspin.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topspin.boot.bean.FormConvite;
import com.topspin.boot.dao.ConviteDao;
import com.topspin.boot.domain.Convite;
import com.topspin.boot.domain.Usuario;

@Service @Transactional(readOnly = false)
public class ConviteServiceImpl implements ConviteService {

	@Autowired
	private ConviteDao conviteDao;
	
	@Override
	public void salva(FormConvite formConvite) {
		
		Usuario usuario = new Usuario();
		usuario.setId(formConvite.getIdUsuario());
		
		Usuario convidado = new Usuario();
		convidado.setId(formConvite.getIdConvidado());
		
		Convite convite = new Convite();
		convite.setUsuario(usuario);
		convite.setConvidado(convidado);
		convite.setData(formConvite.getData());
		convite.setPeriodo(formConvite.getPeriodo());
		convite.setLocalJogo(formConvite.getLocalJogo());
		convite.setDescricao(formConvite.getDescricao());
		convite.setStatus("P");
      	
		conviteDao.save(convite);
	}

	@Override
	public void atualiza(Convite convite) {
		conviteDao.update(convite);
	}
	
	@Override
	public void aceita(Convite convite) {
		Convite conv = conviteDao.findById(convite.getId());
		conv.setStatus("A");
	}

	@Override
	public void recusa(Convite convite) {
		Convite conv = conviteDao.findById(convite.getId());
		conv.setStatus("R");
	}

	@Override
	public void exclui(Long id) {
		conviteDao.delete(id);
	}

	@Override @Transactional(readOnly = true)
	public Convite buscaPorId(Long id) {
		return conviteDao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Convite> listaTodos() {
		return conviteDao.findAll();
	}
	
	@Override @Transactional(readOnly = true)
	public List<Convite> listaPorUsuarioEStatus(Convite convite) {
		return conviteDao.listaPorUsuarioEStatus(convite);
	}
	
	@Override @Transactional(readOnly = true)
	public List<Convite> listaPorConvidadoEStatus(Convite convite) {
		return conviteDao.listaPorConvidadoEStatus(convite);
	}

	@Override
	public List<Convite> listaPorConvidadoENaoPendentes(Convite convite) {
		return conviteDao.listaPorConvidadoENaoPendentes(convite);
	}
	
	@Override
	public int countPorConvidadoEPendentes(Convite convite) {
		convite.setStatus("P");
		return conviteDao.countPorConvidadoEStatus(convite);
	}

}
