package com.test.api.frontend.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankTransactionRequestView {

    BigDecimal transactionAmount;

    Long fromBankAccountId;

    Long toBankAccountId;
}
