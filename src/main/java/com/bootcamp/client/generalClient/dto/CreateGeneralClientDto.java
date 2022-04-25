package com.bootcamp.client.generalClient.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateGeneralClientDto {

    /*@Id
    private String id;*/
    private String description;
    private String abbreviation;
    private String isoCurrencyCode;
    private BigDecimal interesRate;
    //private short registrationStatus;
    //private Date insertionDate = new Date();
    private String fk_insertionUser;
    private String insertionTerminal;

}