package com.example.cbsmiddleware.Currency.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Embeddable
public class Rate {
    @Column(insertable=false, updatable=false)
    private Integer buy;
    @Column(insertable=false, updatable=false)
    private String name;
    @Column(insertable=false, updatable=false)
    private Integer sell;
}
