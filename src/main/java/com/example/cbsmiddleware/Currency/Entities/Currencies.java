package com.example.cbsmiddleware.Currency.Entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Currencies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private CurrencyNames currencyNames;
    private Rate corporateRate ;
    private String currencyOrde;
    private Boolean enabled;
    private String isoCode;
    private String isoNum;
    private Date lastUpdate;
    @Embedded
    private Rate retailRate;
    @Embedded
    private Rate staffRate;
    private Integer unit;
    @Embedded
    private Rate transferRate;


}
//    @Column(name = "code")
//    private String code; Column 'buy' is duplicated in mapping for entity 'com.example.cbsmiddleware.Entities.AccountType.Currencies' (use '@Column(insertable=false, updatable=false)' when mapping multiple properties to the same column)
//	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1786) ~[spring-beans-6.1.5.jar:6.1.5]
//	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:600) ~[spring-beans-6.1.5.jar:6.1.5]
//	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.5.jar:6.1.5]
//	at org.springframework.beans.factory.support.AbstractBean
//
//    @Column(name = "name_code")
//    private String nameCode;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "decimal_places")
//    private Integer decimalPlaces;
//
//    @Column(name = "display_symbol")
//    private String displaySymbol;
//
//    @Column(name = "display_label")
//    private String displayLabel;


