package com.ionx.ionx.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.stereotype.Repository;

import com.ionx.ionx.domain.Produto;

//Componente responsável pelo acesso aos dados
@Repository
public class ProdutoDaoImpl implements ProdutoDao {
	//Gerenciar dependência de um EntityManager, para utilizar um método basta clicar EM mais o nome do método
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void salvar(Produto produto) {
		em.merge(produto);
	}

	@Override
	public List<Produto> recuperar() {
		return em.createQuery("select e from Produto e", Produto.class).getResultList();
	}

	@Override
	public Produto recuperarPorID(long id) {
		return em.find(Produto.class, id);
	}

	@Override
	public void atualizar(@Valid Produto produto) {
		em.merge(produto);
	}

	@Override
	public void excluir(long id) {
		em.remove(em.getReference(Produto.class, id));
	}
	
}
