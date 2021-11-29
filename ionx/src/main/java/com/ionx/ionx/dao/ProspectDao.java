package com.ionx.ionx.dao;

import java.util.List;

import javax.validation.Valid;

import com.ionx.ionx.domain.Prospect;

public interface ProspectDao {
	
	void salvar (Prospect prospect);
	List<Prospect> recuperar();
	Prospect recuperarPorID(long id);
	void atualizar (@Valid Prospect prospect);
	void excluir(long id);

}
