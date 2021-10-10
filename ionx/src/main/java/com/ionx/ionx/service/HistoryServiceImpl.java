package com.ionx.ionx.service;

import com.ionx.ionx.dao.HistoryDao;
import com.ionx.ionx.domain.History;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Representa uma classe de Serviço para o Spring
@Service

//Responsabilidade de gerenciar transações
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryDao historyDao;

    @Autowired
    private ProspectService prospectService;

    @Override
    public void salvar(History history, long prospectId) {
        history.setProspect(prospectService.recuperarPorId(prospectId));
        historyDao.salvar(history);
    }

    @Override
    @Transactional(readOnly = true)
    public List<History> recuperarPorProspect(long prospectId) {
        return historyDao.recuperarPorProspect(prospectId);
    }

    @Override
    @Transactional(readOnly = true)
    public History recuperarPorProspectIdEHistoryId(long prospectId, long historyId) {
        return historyDao.recuperarPorProspectIdEHistoryId(prospectId, historyId);
    }

    @Override
    public void atualizar(History history, long prospectId) {
        history.setProspect(prospectService.recuperarPorId(prospectId));
        historyDao.atualizar(history);
    }

    @Override
    public void excluir(long prospectId, long historyId) {
        historyDao.excluir(recuperarPorProspectIdEHistoryId(prospectId, historyId).getId());
    }

}
