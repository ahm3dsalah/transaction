package com.test.api.frontend.controllers;

import com.test.api.backend.service.BankTransactionService;
import com.test.api.frontend.views.BankTransactionRequestView;
import com.test.api.frontend.views.ResponseView;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.websocket.api.StatusCode;

public class TransactionController {

    private final BankTransactionService bankTransactionService;

    public TransactionController() {
        bankTransactionService = new BankTransactionService();
    }
    public ResponseView transferMoney(BankTransactionRequestView bankTransactionRequestView) {
        bankTransactionService.createBankTransaction(bankTransactionRequestView);

        return new ResponseView("success", HttpStatus.CREATED_201);
    }
}
