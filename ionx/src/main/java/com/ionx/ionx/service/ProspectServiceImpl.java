package com.ionx.ionx.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ionx.ionx.dao.ProspectDao;
import com.ionx.ionx.domain.Prospect;
import com.ionx.ionx.repositories.ProspectRepository;

@Service
@Transactional
public class ProspectServiceImpl implements ProspectService {
	
	@Autowired
    private ProspectDao prospectDao;
	@Autowired
    private ProspectRepository prospectRepository;

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
	    public Optional<Prospect> findById(long id) {
	        return prospectRepository.findById(id);
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
    public Prospect saveProspect(Prospect prospect) {
       return prospectRepository.save(prospect);
    }

	@Override
	public void atualizar(@Valid long id) {
		// TODO Auto-generated method stub
		
	}
	
}
