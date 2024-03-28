package com.example.cbsmiddleware.Currency.Entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
public class Date {

   private  Integer day;
    private  Integer  fractionalSecond;
    private  Integer hour;
    private  Integer millisecond;
    private  Integer minute;
    private  Integer month;
    private  Integer  second;
    private  Integer timezone;
    private  Integer  year;
}
