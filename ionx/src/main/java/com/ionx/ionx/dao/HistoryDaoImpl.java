package com.ionx.ionx.dao;

import com.ionx.ionx.domain.History;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HistoryDaoImpl implements HistoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(History history) {
        em.merge(history);
    }

    @Override
    public List<History> recuperarPorProspect(long prospectId) {
        return em.createQuery("select m from History m where m.prospect.id = :prospectId", History.class)
                .setParameter("prospectId", prospectId)
                .getResultList();
    }

    @Override
    public History recuperarPorProspectIdEHistoryId(long prospectId, long historyId) {
        return em.createQuery("select m from History m where m.prospect.id = :prospectId and m.id = :historyId", History.class)
                .setParameter("prospectId", prospectId)
                .setParameter("historyId", historyId)
                .getSingleResult();
    }

    @Override
    public void atualizar(History history) {
        em.merge(history);
    }

    @Override
    public void excluir(long historyId) {
        em.remove(em.getReference(History.class, historyId));
    }

}