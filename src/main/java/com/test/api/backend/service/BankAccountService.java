
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
        BankAccount bankAccount = bankAccountDao.getById(bankAccountId);

        BankAccountView bankAccountView =  new BankAccountView.BankAccountViewBuilder()
                .withId(bankAccount.getId())
                .withBalance(bankAccount.getBalance())
                .build();

        return bankAccountView;
    }

    public BankAccount persistBankAccount(BankAccount bankAccount) {

        bankAccount = bankAccountDao.save(bankAccount);
        return bankAccount;
    }
}
