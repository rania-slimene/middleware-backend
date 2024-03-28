package com.example.cbsmiddleware.Currency.Dto;

import lombok.Data;

@Data
public class CurrencyDtoFineract {
    private String code  ;
    private String nameCode  ;
    private String name  ;
    private Integer decimalPlaces  ;
    private String displaySymbol;
    private Integer  inMultiplesOf;
    private String displayLabel;


}
