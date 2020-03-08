package com.test.api.frontend.controllers;

import com.test.api.backend.service.BankAccountService;
import com.test.api.frontend.views.BankAccountView;
import com.test.api.frontend.views.CreateBankAccountRequestView;
import spark.Request;

public class BankAccountController {

    final BankAccountService bankAccountService;

    public BankAccountController() {
        this.bankAccountService = new BankAccountService();
    }

    public BankAccountView createBankAccount(CreateBankAccountRequestView requestView) {
        BankAccountView bankAccountView = bankAccountService.persistBankAccount(requestView);
        return bankAccountView;
    }

    public BankAccountView getBankAccount(Request request) {
        Long bankAccountId = Long.parseLong(request.splat()[0]);

        BankAccountView bankAccount = bankAccountService.getBankAccountById(bankAccountId);

        return bankAccount;

    }
}
