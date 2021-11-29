package com.ionx.ionx.service;

import com.ionx.ionx.domain.History;

import java.util.List;


public interface HistoryService {

    void salvar(History history, long prospectId);
    List<History> recuperarPorProspect(long prospectId);
    History recuperarPorProspectIdEHistoryId(long prospectId, long historyId);
    void atualizar(History history, long prospectId);
    void excluir(long prospectId, long historyId);

}
