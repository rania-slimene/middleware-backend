package com.example.cbsmiddleware.Country.Dto;

import lombok.Data;

@Data
public class CountryDto {

    private String iso2Code ;
    private String iso3Code ;
    private String isoCode ;
    private String countryNameFr ;
    private String countryNameEn ;
    private Boolean  enabled ;
}
