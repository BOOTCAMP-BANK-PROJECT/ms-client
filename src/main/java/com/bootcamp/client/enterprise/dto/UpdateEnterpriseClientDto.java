package com.bootcamp.client.enterprise.dto;

import com.bootcamp.client.general.entity.GenericProduct;
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
    private List<GenericProduct> accounts;
    private String profile;

    /*private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;*/

}