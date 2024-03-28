package com.example.cbsmiddleware.Currency.Dto;

import com.example.cbsmiddleware.Currency.Entities.CurrencyNames;
import com.example.cbsmiddleware.Currency.Entities.Date;
import com.example.cbsmiddleware.Currency.Entities.Rate;
import lombok.Data;

@Data
public class CurrencyDto {

    private CurrencyNames currencyNames;
    private Rate corporateRate ;
    private String currencyOrde;
    private Boolean enabled;
    private String isoCode;
    private String isoNum;
    private Date lastUpdate;
    private Rate retailRate;
    private Rate staffRate;
    private Integer unit;
    private Rate transferRate;

}
