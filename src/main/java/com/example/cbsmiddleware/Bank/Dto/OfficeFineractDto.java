package com.example.cbsmiddleware.Bank.Dto;

import lombok.Data;

@Data
public class OfficeFineractDto {

      private String   name;
      private String  externalId;
      private String   openingDate;
      private String   dateFormat;
      private String   locale ;
      private Integer  parentId;

}
