package com.ionx.ionx.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ionx.ionx.dao.ProdutoDao;
import com.ionx.ionx.domain.Produto;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
    private ProdutoDao produtoDao;

	@Override
	public void salvar(Produto produto) {
		produtoDao.salvar(produto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> recuperar() {
		return produtoDao.recuperar();
		
	}

	@Override
	@Transactional(readOnly = true)
	public Produto recuperarPorId(long id) {
		return produtoDao.recuperarPorID(id);
	}

	@Override
	public void atualizar(Produto prospect) {
		produtoDao.atualizar(prospect);
	}

	@Override
	public void excluir(long id) {
		produtoDao.excluir(id);
	}

	@Override
	public void atualizar(@Valid long id) {
		// TODO Auto-generated method stub
		
	}
	
}
