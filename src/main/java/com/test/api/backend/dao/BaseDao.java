package com.test.api.backend.dao;

import com.test.api.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BaseDao {

    private Session session;

    private Transaction transaction;

    public Session openSession() {
        session = getSessionFactory().openSession();
        return session;
    }

    public Session openSessionWithTransaction() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeCurrentSession() {
        session.close();
    }

    public void closeSessionWithTransaction() {
        transaction.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
