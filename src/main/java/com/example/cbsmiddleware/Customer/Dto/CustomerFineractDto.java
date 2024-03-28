package com.example.cbsmiddleware.Customer.Dto;
import lombok.Data;

@Data
public class CustomerFineractDto {
    private String dateOfBirth;
    private  String firstname;
    private  String middlename;
    private String lastname;
    private String mobileNo;
    private Integer officeId;
    private Boolean active;
    private String submittedOnDate;
    private String dateFormat;
    private String locale;
    private Integer legalFormId;
    private String  externalId;
}
