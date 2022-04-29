package com.bootcamp.client.personalClient.dto;

import com.bootcamp.client.generalClient.entity.GenericAccount;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class CreatePersonalClientDto {

    /*@Id
    private String id;*/

    private String documentType;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String residenceAddress;
    private List<GenericAccount> accounts;

    //private short registrationStatus;
    //private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;

}