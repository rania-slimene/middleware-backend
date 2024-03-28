package com.example.cbsmiddleware.PermanentTransferManagement.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PermanentTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer amount;
    private String beneficiaryFullName;
    private String beneficiaryUniqueName;
    private String currency;
    private String customerNumber;
    private String firstExecutionDate;
    private String lastExecutionDate;
    private  String periodicity;//annual dans cbs
    private  String photoUrl;
    private  String reason;
    private  String ribReceiver;
    private  String ribSender;
    private  String status;
    private  String transactionNumber;
    private  String transferType;

}
