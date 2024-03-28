package com.example.cbsmiddleware.Transaction.Entities;

import com.example.cbsmiddleware.Account.Entities.Account;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer txid ;
    private String txReference;
    private String txOperationCode;
    private String txOperationType ;
    private String txDescription;
    private String txChannel;
    private String txFromCurrency ;
    private String txToCurrency ;
    private BigDecimal txConvertedAmount ;
    private LocalDateTime txtDate;
    private BigDecimal txAmount;
    private LocalDate txDueDate;
    private LocalDate txValueDate;
    private BigDecimal txAppliedCurrencyRate;
    private String txReason;
    private String txStatus;
    private BigDecimal txFee;
    private BigDecimal feeType;
    private BigDecimal txCurrentBalance;
    private BigDecimal txAvailableBalance;
    private BigDecimal txOnHoldAmount;
    private String txType;
    private String transactionOperation;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Account account;

}
