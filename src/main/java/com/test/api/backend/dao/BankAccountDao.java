package com.test.api.backend.dao;

import com.test.api.entity.BankAccount;

public class BankAccountDao extends BaseDao {

    public BankAccount save(BankAccount bankAccount) {
        getSession().save(bankAccount);
        return bankAccount;
    }

    public BankAccount getById(long id) {
        BankAccount bankAccount = getSession().get(BankAccount.class, id);
        return bankAccount;
    }
}
