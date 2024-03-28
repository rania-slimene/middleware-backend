package com.example.cbsmiddleware.Customer.Dto;


import lombok.Data;

@Data
public class ActivatedCutomer {
    private String activationDate;
    public String dateFormat;
    public String locale;
}
