package com.test.api.frontend.controllers;

import com.test.api.backend.service.BankTransactionService;
import com.test.api.frontend.views.BankTransactionRequestView;
import com.test.api.frontend.views.TransactionSummaryView;

public class TransactionController {

    private final BankTransactionService bankTransactionService;

    public TransactionController() {
        bankTransactionService = new BankTransactionService();
    }
    public TransactionSummaryView transferMoney(BankTransactionRequestView bankTransactionRequestView) {
        return bankTransactionService.createBankTransaction(bankTransactionRequestView);
    }
}
