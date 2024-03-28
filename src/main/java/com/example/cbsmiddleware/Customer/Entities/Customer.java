package com.example.cbsmiddleware.Customer.Entities;
import com.example.cbsmiddleware.Account.Entities.Account;
import com.example.cbsmiddleware.Customer.Enum.ClientStatus;
import com.example.cbsmiddleware.Customer.Enum.Gender;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    private String CustomerNumber;
    private String firstName;
    private String lastName;
    private String nickName;
    private Integer cin;
    private Gender gender;
    private String birthDate;
    private String birthCity;
    private String nationality;
    private String postCode;
    private String mobile;
    private String email;
    private String fax;
    private String city;
    private String fullAddress;
    private String language;
    private byte profileImage;
    private Boolean isVIP;
    private String joinDate;
    private String password;
    private ClientStatus customerStatus;
//@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//List<Account> accountList ;





}
