package com.ionx.ionx.service;

import java.util.List;

import javax.validation.Valid;

import com.ionx.ionx.domain.Prospect;

public interface ProspectService {
	void salvar(Prospect prospect);
	List<Prospect> recuperar();
	Prospect recuperarPorId(long id);
	void atualizar(@Valid long id);
	void excluir(long id);
	void atualizar(Prospect prospect);
}
