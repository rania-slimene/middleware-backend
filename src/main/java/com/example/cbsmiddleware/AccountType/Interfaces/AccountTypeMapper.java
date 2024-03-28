package com.example.cbsmiddleware.AccountType.Interfaces;

import com.example.cbsmiddleware.AccountType.Dto.ProductSavingFineractDto;
import com.example.cbsmiddleware.AccountType.Entities.AccountType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component

@Mapper
public interface AccountTypeMapper {

    @Mapping(source = "accountTypeName", target = "name")
   // @Mapping(target = "shortName", expression = "java(ShortNameGenerator.generateShortName())")
    @Mapping(target = "shortName", constant = "abc")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "currencyCode", constant = "USD")
    @Mapping(target = "inMultiplesOf", constant = "1")
    @Mapping(target = "digitsAfterDecimal", constant = "2")
    @Mapping(target = "locale", constant = "en")
    @Mapping(source = "interestRate", target = "nominalAnnualInterestRate")
    @Mapping(target = "interestCompoundingPeriodType", constant = "7")
    @Mapping(target = "interestPostingPeriodType", constant = "7")
    @Mapping(target = "interestCalculationType", constant = "1")
    @Mapping(target = "interestCalculationDaysInYearType", constant = "365")
    @Mapping(target = "accountingRule", constant = "1")
    ProductSavingFineractDto mapToFineract(AccountType cbsAccountType);
}
