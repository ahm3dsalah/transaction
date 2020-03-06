package com.test.api;


import com.test.api.backend.service.BankAccountService;

import com.test.api.entity.BankAccount;
import org.apache.log4j.Logger;
import com.test.api.util.SparkUtils;

import java.math.BigDecimal;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Main.class);
        SparkUtils.createServerWithRequestLog(logger);

        BankAccountService bankAccountService = new BankAccountService();



        get("/hello", (request, response) -> persistBankAccount());

        get("/bankAccount", (request, response) -> bankAccountService.getBankAccountById(1L).toString());
    }



    private static String persistBankAccount(){

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new BigDecimal("2000"));

        BankAccount bankAccount1 = new BankAccountService().persistBankAccount(bankAccount);

        return bankAccount1.toString();
    }


}
