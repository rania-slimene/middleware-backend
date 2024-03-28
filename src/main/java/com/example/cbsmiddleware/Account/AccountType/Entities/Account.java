package com.example.cbsmiddleware.Account.AccountType.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer accountTypeId;
        private String accountTypeName;
        private String description;
        private double interestRate;
        private double maintenanceFee;
        private String maintenancePeriodicity;
    }

