package com.test.api.backend.dao;

import com.test.api.entity.BankTransaction;

public class BankTransactionDao extends BaseDao{

    public void save(BankTransaction bankTransaction){
        getSession().save(bankTransaction);
    }

}
