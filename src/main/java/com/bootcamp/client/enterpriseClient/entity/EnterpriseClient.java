package com.bootcamp.client.enterpriseClient.entity;

import com.bootcamp.client.generalClient.entity.GenericAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class EnterpriseClient {

    @Id
    private String id;
    private String ruc;
    private String companyName;
    private String legalResidence;
    private List<GenericAccount> accounts;

    private short registrationStatus;
    private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;

}
