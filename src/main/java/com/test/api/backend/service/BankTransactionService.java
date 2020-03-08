package com.test.api.backend.service;

import com.test.api.backend.dao.BankAccountDao;
import com.test.api.backend.dao.BankTransactionDao;
import com.test.api.entity.BankAccount;
import com.test.api.entity.BankTransaction;
import com.test.api.frontend.views.BankTransactionRequestView;
import com.test.api.frontend.views.TransactionSummaryView;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankTransactionService {

    private final BankAccountDao bankAccountDao;

    private final BankTransactionDao bankTransactionDao;

    public BankTransactionService() {
        bankAccountDao = new BankAccountDao();
        bankTransactionDao = new BankTransactionDao();
    }

    public TransactionSummaryView createBankTransaction(BankTransactionRequestView requestView) {

        // get bank accounts
        Session session = bankAccountDao.openSessionWithTransaction();
        Transaction transaction = bankAccountDao.getTransaction();

        BankAccount fromBankAccount = bankAccountDao.getById(requestView.getFromBankAccountId());

        BankAccount toBankAccount = bankAccountDao.getById(requestView.getToBankAccountId());

        // update balances
        fromBankAccount.setBalance(fromBankAccount.getBalance().subtract(requestView.getTransactionAmount()));

        toBankAccount.setBalance(toBankAccount.getBalance().add(requestView.getTransactionAmount()));

        // create bank transaction
        BankTransaction bankTransaction =  new BankTransaction();
        bankTransaction.setAmount(requestView.getTransactionAmount());
        bankTransaction.setFrom(fromBankAccount);
        bankTransaction.setTo(toBankAccount);

        bankTransactionDao.setSession(session);
        bankTransactionDao.setTransaction(transaction);

        bankTransactionDao.save(bankTransaction);


        bankAccountDao.save(fromBankAccount);
        bankAccountDao.save(toBankAccount);

        //close transaction
        bankAccountDao.closeSessionWithTransaction();

        return null;
    }
}
