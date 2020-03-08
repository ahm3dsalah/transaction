
package com.test.api.backend.service;

import com.test.api.backend.dao.BankAccountDao;
import com.test.api.entity.BankAccount;
import com.test.api.frontend.mappers.BankAccountViewMapper;
import com.test.api.frontend.views.BankAccountView;
import com.test.api.frontend.views.CreateBankAccountRequestView;

public class BankAccountService {

    private final BankAccountDao bankAccountDao;

    private final BankAccountViewMapper bankAccountViewMapper;

    public BankAccountService() {

        this.bankAccountDao = new BankAccountDao();
        bankAccountViewMapper = new BankAccountViewMapper();
    }


    public BankAccountView getBankAccountById(Long bankAccountId) {
        bankAccountDao.openSession();
        BankAccount bankAccount = bankAccountDao.getById(bankAccountId);
        bankAccountDao.closeCurrentSession();

        return bankAccountViewMapper.map(bankAccount);
    }

    public BankAccountView persistBankAccount(CreateBankAccountRequestView bankAccountRequestView) {

        BankAccount newBankAccount = new BankAccount();

        newBankAccount.setBalance(bankAccountRequestView.getBalance());

        bankAccountDao.openSessionWithTransaction();
        newBankAccount = bankAccountDao.save(newBankAccount);
        bankAccountDao.closeSessionWithTransaction();
        return bankAccountViewMapper.map(newBankAccount);
    }
}
