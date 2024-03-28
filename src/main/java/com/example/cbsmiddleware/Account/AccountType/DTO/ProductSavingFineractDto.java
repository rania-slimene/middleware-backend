package com.example.cbsmiddleware.Account.AccountType.DTO;

import lombok.Data;

@Data

public class ProductSavingFineractDto {

        public  String name;
        public String shortName;
        public String description;
        public String currencyCode;
        public Integer digitsAfterDecimal;
        public Integer inMultiplesOf;
        public String locale;
        public Double nominalAnnualInterestRate;
        public Integer interestCompoundingPeriodType;
        public Integer interestPostingPeriodType;
        public Integer interestCalculationType;
        public Integer interestCalculationDaysInYearType;
        public Integer accountingRule;



}
