package com.example.cbsmiddleware.Currency.Entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CurrencyNames {
    private String ar;
    private String en;
    private String fr;

}
