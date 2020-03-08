package com.test.api.backend.dao;

import com.test.api.entity.BankTransaction;

public class BankTransactionDao extends BaseDao{

    public BankTransaction getById(Long id) {
        return getSession().load(BankTransaction.class, id);
    }

    public void save(BankTransaction bankTransaction){
        getSession().save(bankTransaction);
    }

}
