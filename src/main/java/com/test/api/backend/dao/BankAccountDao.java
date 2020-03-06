package com.test.api.backend.dao;

import com.test.api.entity.BankAccount;
import com.test.api.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankAccountDao {

    public BankAccountDao() {
    }

    public BankAccount save(BankAccount bankAccount) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(bankAccount);
        transaction.commit();
        session.close();

        return bankAccount;
    }

    public BankAccount getById(long id) {
        Session session = HibernateUtils.getSession();
        BankAccount bankAccount = session.get(BankAccount.class, id);
        session.close();
        return bankAccount;
    }
}
