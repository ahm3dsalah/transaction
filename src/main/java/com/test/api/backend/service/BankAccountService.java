package com.test.api.backend.service;

import com.test.api.backend.dao.BankTransactionDao;
import com.test.api.entity.BankAccount;
import com.test.api.entity.BankTransaction;
import com.test.api.frontend.views.BankAccountView;

public class BankAccountService {

    private final BankTransactionDao bankTransactionDao;

    public BankAccountService() {
        this.bankTransactionDao = new BankTransactionDao();
    }
    public BankAccountView getBankAccountById(Long bankAccountId) {
        bankTransactionDao.openCurrentSession();
        BankAccount bankAccount = bankTransactionDao.getById(bankAccountId);
        bankTransactionDao.closeCurrentSession();

        return new BankAccountView.BankAccountViewBuilder()
                .withId(bankAccount.getId())
                .withBalance(bankAccount.getBalance())
                .build();
    }
}
