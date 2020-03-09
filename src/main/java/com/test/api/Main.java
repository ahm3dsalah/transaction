package com.test.api;


import com.test.api.frontend.controllers.BankAccountController;
import com.test.api.frontend.controllers.TransactionController;
import com.test.api.frontend.views.BankTransactionRequestView;
import com.test.api.frontend.views.CreateBankAccountRequestView;
import com.test.api.util.JsonResponseTransformer;
import com.test.api.util.RequestMapperUtil;
import com.test.api.util.SparkUtils;
import org.apache.log4j.Logger;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Main.class);
        SparkUtils.createServerWithRequestLog(logger);


        BankAccountController bankAccountController = new BankAccountController();
        TransactionController transactionController = new TransactionController();

        post("/bankAccount", (request, response) ->
                        bankAccountController.createBankAccount(new RequestMapperUtil<CreateBankAccountRequestView>().mapRequest(request, CreateBankAccountRequestView.class)),
                new JsonResponseTransformer());

        get("/bankAccount/*", (request, response) -> bankAccountController.getBankAccount(request), new JsonResponseTransformer());

        post("/transaction", (request, response) -> transactionController.transferMoney(new RequestMapperUtil<BankTransactionRequestView>().mapRequest(request, BankTransactionRequestView.class)),
                new JsonResponseTransformer());
    }
}
