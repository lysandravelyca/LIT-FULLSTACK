/**
 * Performance Optimization - Hibernate Performance
 * 
 * Challenge: Optimize Hibernate queries for better performance
 * 
 * TODO: Implement performance optimizations
 */

package com.kulkultech.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class PerformanceOptimization {
    
    public void batchInsert(Session session, List<?> entities) {
        Transaction tx = session.beginTransaction();
        int batchSize = 20;

        for (int i = 0; i < entities.size(); i++) {
            session.persist(entities.get(i));

            if (i > 0 && i % batchSize == 0) {
                session.flush();
                session.clear();
            }
        }

        tx.commit();
    }

    public List<?> findAllWithTrades(Session session) {
        String hql = "SELECT p FROM PortfolioStub p JOIN FETCH p.trades";
        Query<?> query = session.createQuery(hql);
        return query.getResultList();
    }
    
    public List<Object[]> findAccountNamesOnly(Session session) {
        String hql = "SELECT t.id, t.accountName FROM TradingAccountStub t";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        return query.getResultList();
    }
}

