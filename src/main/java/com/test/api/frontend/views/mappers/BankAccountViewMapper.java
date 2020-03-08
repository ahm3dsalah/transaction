package com.test.api.frontend.views.mappers;

import com.test.api.entity.BankAccount;
import com.test.api.frontend.views.BankAccountView;

public class BankAccountViewMapper {

    public BankAccountView map(BankAccount bankAccount) {

        BankAccountView bankAccountView = new BankAccountView();
        bankAccountView.setId(bankAccount.getId());
        bankAccountView.setBalance(bankAccount.getBalance());

        return bankAccountView;
    }
}
