package com.example.cbsmiddleware.Account.Dto;

import lombok.Data;
import lombok.ToString;

@Data
public class SavingsAccountStatusDTO {
    private Integer id;
    private String code;
    private String value;
    private Boolean submittedAndPendingApproval;
    private Boolean approved;
    private Boolean rejected;
    private Boolean withdrawnByApplicant;
    private Boolean active;
    private Boolean closed;
    private Boolean prematureClosed;
    private Boolean transferInProgress;
    private Boolean transferOnHold;
    private Boolean matured;
}