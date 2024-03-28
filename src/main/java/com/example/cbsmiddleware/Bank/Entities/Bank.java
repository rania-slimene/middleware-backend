package com.example.cbsmiddleware.Bank.Entities;

import com.example.cbsmiddleware.Account.Entities.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String code ;
    private String bankBic ;
    private String bankName;
    private String bankEmail;
    private String bankPhone ;
    private String address ;
    private String countryName ;
    private String cityName ;
    private double latitude ;
    private double longitude ;
    private String createdDate;
    private LocalDateTime lastUpdated;
    private byte bankLogo;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bank")
    @JsonIgnore
    List<Account> accounts;
}
