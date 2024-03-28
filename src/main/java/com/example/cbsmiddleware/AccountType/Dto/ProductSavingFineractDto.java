package com.example.cbsmiddleware.AccountType.Dto;

import lombok.Data;

@Data

public class ProductSavingFineractDto {

        private  String name;
        private String shortName;
        private String description;
        private String currencyCode;
        private Integer digitsAfterDecimal;
        private Integer inMultiplesOf;
        private String locale;
        private Double nominalAnnualInterestRate;
        private Integer interestCompoundingPeriodType;
        private Integer interestPostingPeriodType;
        private Integer interestCalculationType;
        private Integer interestCalculationDaysInYearType;
        private Integer accountingRule;



}
