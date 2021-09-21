package com.ionx.ionx.service;

import java.util.List;

import javax.validation.Valid;

import com.ionx.ionx.domain.Produto;

public interface ProdutoService {
	void salvar(Produto produto);
	List<Produto> recuperar();
	Produto recuperarPorId(long id);
	void atualizar(@Valid long id);
	void excluir(long id);
	void atualizar(Produto produto);
}
