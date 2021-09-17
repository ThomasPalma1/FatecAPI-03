package com.ionx.ionx.dao;

import java.util.List;

import javax.validation.Valid;

import com.ionx.ionx.domain.Produto;

public interface ProdutoDao {
		
		void salvar (Produto produto);
		List<Produto> recuperar();
		Produto recuperarPorID(long id);
		void atualizar (@Valid Produto produto);
		void excluir(long id);
}
