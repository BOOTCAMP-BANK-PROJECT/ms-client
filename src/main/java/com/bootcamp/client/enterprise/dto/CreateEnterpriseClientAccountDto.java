package com.bootcamp.client.enterprise.dto;

import com.bootcamp.client.general.entity.GenericAccount;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class CreateEnterpriseClientAccountDto {

    @Id
    private String accountId;
    private String accountType;
    private String accountUrl;
    private String accountIsoCurrencyCode;

    //private short accountRegistrationStatus;
    //private Date accountInsertionDate;
    private String accountFk_insertionUser;
    private String accountInsertionTerminal;

}