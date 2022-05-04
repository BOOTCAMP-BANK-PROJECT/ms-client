package com.bootcamp.client.enterprise.dto;

import com.bootcamp.client.general.entity.GenericProduct;
import lombok.Data;
import java.util.List;

@Data
public class CreateEnterpriseClientDto {

    /*@Id
    private String id;*/
    private String ruc;
    private String companyName;
    private String legalResidence;
    private List<GenericProduct> accounts;
    private String profile;

    //private short registrationStatus;
    //private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;

}