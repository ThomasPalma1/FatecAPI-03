package com.ionx.ionx.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.stereotype.Repository;

import com.ionx.ionx.domain.Prospect;

//Componente responsável pelo acesso aos dados
@Repository
public class ProspectDaoImpl implements ProspectDao {
	//Gerenciar dependência de um EntityManager, para utilizar um método basta clicar EM mais o nome do método
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void salvar(Prospect prospect) {
		em.merge(prospect);
	}

	@Override
	public List<Prospect> recuperar() {
		return em.createQuery("select p from Prospect p", Prospect.class).getResultList();
	}

	@Override
	public Prospect recuperarPorID(long id) {
		return em.find(Prospect.class, id);
	}

	@Override
	public void atualizar(@Valid Prospect prospect) {
		em.merge(prospect);
	}

	@Override
	public void excluir(long id) {
		em.remove(em.getReference(Prospect.class, id));
	}
	
}
