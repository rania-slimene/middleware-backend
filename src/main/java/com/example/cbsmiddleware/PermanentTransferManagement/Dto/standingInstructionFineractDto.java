package com.example.cbsmiddleware.PermanentTransferManagement.Dto;

import lombok.Data;
@Data
public class standingInstructionFineractDto {
    public Integer fromOfficeId;
    public Integer fromClientId;
    public Integer fromAccountType;
    public String name;
    public Integer transferType;
    public Integer priority;
    public Integer status;
    public Integer fromAccountId;
    public Integer toOfficeId;
    public Integer toClientId;
    public Integer toAccountType;
    public Integer toAccountId;
    public Integer instructionType;
    public String amount;
    public String validFrom;
    public Integer recurrenceType;
    public String recurrenceInterval;
    public Integer recurrenceFrequency;
    public String validTill;
    public String locale;
    public String dateFormat;
    public String recurrenceOnMonthDay;
    public String monthDayFormat;
}