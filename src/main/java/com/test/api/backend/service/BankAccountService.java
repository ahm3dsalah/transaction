package com.test.api.backend.service;

import com.test.api.backend.dao.BankAccountDao;
import com.test.api.entity.BankAccount;
import com.test.api.frontend.views.BankAccountView;

public class BankAccountService {

    private final BankAccountDao bankAccountDao;

    public BankAccountService() {
        this.bankAccountDao = new BankAccountDao();
    }
    public BankAccountView getBankAccountById(Long bankAccountId) {
        bankAccountDao.openCurrentSession();
        BankAccount bankAccount = bankAccountDao.getById(bankAccountId);
        bankAccountDao.closeCurrentSession();

        return new BankAccountView.BankAccountViewBuilder()
                .withId(bankAccount.getId())
                .withBalance(bankAccount.getBalance())
                .build();
    }

    public BankAccount persistBankAccount(BankAccount bankAccount) {
        bankAccountDao.openCurrentSessionwithTransaction();
        bankAccountDao.save(bankAccount);
        bankAccountDao.closeCurrentSessionwithTransaction();

        return bankAccount;
    }

    public BankAccount getBankAccount(long id) {
        bankAccountDao.openCurrentSession();
        BankAccount bankAccount = bankAccountDao.getById(id);
        bankAccountDao.closeCurrentSession();

        return bankAccount;
    }
}
