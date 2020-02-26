package com.test.api.frontend.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountView {

    private Long id;

    private BigDecimal balance;


    public static final class BankAccountViewBuilder {
        private Long id;
        private BigDecimal balance;

        public BankAccountViewBuilder() {
        }

        public static BankAccountViewBuilder aBankAccountView() {
            return new BankAccountViewBuilder();
        }

        public BankAccountViewBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public BankAccountViewBuilder withBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public BankAccountView build() {
            BankAccountView bankAccountView = new BankAccountView();
            bankAccountView.setId(id);
            bankAccountView.setBalance(balance);
            return bankAccountView;
        }
    }
}
