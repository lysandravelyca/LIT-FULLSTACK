/**
 * HQL Query Service - Hibernate Query Language
 * 
 * Challenge: Use HQL to query entities instead of SQL
 * 
 * TODO: Implement HQL queries
 */
package com.kulkultech.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class HQLQueryService {

    public List<?> findAllAccounts(Session session) {
        Query<?> query = session.createQuery("FROM TradingAccountStub");
        return query.list();
    }

    
    public List<?> findAccountsByType(Session session, String accountType) {
        Query<?> query = session.createQuery("FROM TradingAccountStub WHERE accountType = :type");
        query.setParameter("type", accountType);
        return query.list();
    }

    public Long countAccounts(Session session) {
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM TradingAccountStub", Long.class);
        return query.uniqueResult();
    }
}
