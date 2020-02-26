package com.test.api.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "bank_transaction")
@Getter
@Setter
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bt_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bt_from_bank_account_id")
    private BankAccount from;

    @OneToOne
    @JoinColumn(name = "bt_to_bank_account_id")
    private BankAccount to;

    @Column(name = "bt_amount")
    private BigDecimal amount;
}
