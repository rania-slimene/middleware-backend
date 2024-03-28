package com.example.cbsmiddleware.Account.Entities;

import com.example.cbsmiddleware.AccountType.Entities.AccountType;
import com.example.cbsmiddleware.Bank.Entities.Bank;
import com.example.cbsmiddleware.Customer.Entities.Customer;
import com.example.cbsmiddleware.Transaction.Entities.Transaction;
import com.example.cbsmiddleware.Account.Enum.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId ;
    private String accountNumber ;
    private String accountCode ;
    private AccountStatus accountStatus;
    private String accountSubType;
    private String creatioDate ;
    private String  accountCodeSecurity;
    private LocalDate lastClosingDate ;
    private String OwnerName;
    private String iban;
    private String rib;
    private String securityCode ;
    private String swift ;
    private String bic ;
    private BigDecimal availableBlance;
    private BigDecimal currentBlance;
    private Boolean favorite;
    private BigDecimal creditLimit;
    private Boolean eligibleCheckBook;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal onHoldAmount;
    private BigDecimal upperCreditLimit;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)

    private AccountType accountType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Bank bank;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account" )
    List<Transaction> transactions;
// @JoinColumn(name = "customer_id")
//    @ManyToOne(fetch = FetchType.LAZY , optional = false)
//    private Customer customer ;
}