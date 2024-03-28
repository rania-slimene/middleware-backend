package com.example.cbsmiddleware.AccountType.Entities;

import com.example.cbsmiddleware.Account.Entities.Account;
import com.example.cbsmiddleware.AccountType.Enum.Periodicite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AccountType {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer accountTypeId;
        private String accountTypeName;
        private String description;
        private double interestRate;
        private double maintenanceFee;
        private Periodicite maintenancePeriodicity;

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountType" )
        @JsonIgnore
        List<Account> accounts;
    }