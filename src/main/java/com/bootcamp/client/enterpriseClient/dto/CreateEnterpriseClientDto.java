package com.bootcamp.client.enterpriseClient.dto;

import com.bootcamp.client.generalClient.entity.GenericAccount;
import lombok.Data;
import java.util.List;

@Data
public class CreateEnterpriseClientDto {

    /*@Id
    private String id;*/
    private String ruc;
    private String companyName;
    private String legalResidence;
    private List<GenericAccount> accounts;

    //private short registrationStatus;
    //private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;

}