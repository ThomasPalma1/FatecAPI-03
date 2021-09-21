package com.ionx.ionx.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ionx.ionx.dao.ProspectDao;
import com.ionx.ionx.domain.Prospect;

@Service
@Transactional
public class ProspectServiceImpl implements ProspectService {
	
	@Autowired
    private ProspectDao prospectDao;

	@Override
	public void salvar(Prospect prospect) {
		prospectDao.salvar(prospect);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prospect> recuperar() {
		return prospectDao.recuperar();
		
	}

	@Override
	@Transactional(readOnly = true)
	public Prospect recuperarPorId(long id) {
		return prospectDao.recuperarPorID(id);
	}

	@Override
	public void atualizar(Prospect prospect) {
		prospectDao.atualizar(prospect);
	}

	@Override
	public void excluir(long id) {
		prospectDao.excluir(id);
	}

	@Override
	public void atualizar(@Valid long id) {
		// TODO Auto-generated method stub
		
	}
	
}
