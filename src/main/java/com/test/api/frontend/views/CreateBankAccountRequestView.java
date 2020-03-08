package com.test.api.frontend.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBankAccountRequestView {

    private BigDecimal balance;


    public static final class CreateBankAccountRequestViewBuilder {
        private BigDecimal balance;

        private CreateBankAccountRequestViewBuilder() {
        }

        public static CreateBankAccountRequestViewBuilder aCreateBankAccountRequestView() {
            return new CreateBankAccountRequestViewBuilder();
        }

        public CreateBankAccountRequestViewBuilder withBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public CreateBankAccountRequestView build() {
            CreateBankAccountRequestView createBankAccountRequestView = new CreateBankAccountRequestView();
            createBankAccountRequestView.setBalance(balance);
            return createBankAccountRequestView;
        }
    }
}
