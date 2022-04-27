package com.bootcamp.client.enterpriseClient.dto;

import com.bootcamp.client.generalClient.entity.GenericAccount;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class UpdateEnterpriseClientDto {

    @Id
    private String id;
    private String ruc;
    //private String companyName;
    private String legalResidence;
    private List<GenericAccount> accounts;

    /*private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;*/

}