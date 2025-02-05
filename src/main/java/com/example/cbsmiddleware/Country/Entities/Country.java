package com.example.cbsmiddleware.Country.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id ;
    private String iso2Code ;
    private String iso3Code ;
    private String isoCode ;
    private String countryName ;
    private Boolean  enabled ;


}
