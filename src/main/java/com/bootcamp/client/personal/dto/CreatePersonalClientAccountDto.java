package com.bootcamp.client.personal.dto;

import com.bootcamp.client.general.entity.GenericAccount;
import lombok.Data;

import java.util.List;

@Data
public class CreatePersonalClientAccountDto {

    /*@Id
    private String id;*/

    // String documentType;
    private String documentNumber;
    //private String lastName;
    //private String firstName;
    //private String residenceAddress;
    private List<GenericAccount> accounts;

    /*private short registrationStatus;
    private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;*/

}