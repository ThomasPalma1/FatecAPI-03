package com.ionx.ionx.dao;


import com.ionx.ionx.domain.History;

import java.util.List;


public interface HistoryDao{
	void salvar(History history);
    List<History> recuperarPorProspect(long prospectId);
    History recuperarPorProspectIdEHistoryId(long prospectId, long historyId);
    void atualizar(History history);
    void excluir(long historyId);
}
