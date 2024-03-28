package com.example.cbsmiddleware.Account.Dto;

import lombok.Data;

@Data
public class SavingsaccountsDtoFineract {
    private Integer productId;
    private Integer clientId;
    private String dateFormat;
    private String locale;
    private String externalId;
    private String  fieldOfficerId;
    private String submittedOnDate;
    private Double nominalAnnualInterestRate;
    private Integer interestCompoundingPeriodType;
    private Integer interestPostingPeriodType;
    private Integer interestCalculationType;
    private Integer interestCalculationDaysInYearType;

}