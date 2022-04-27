package com.bootcamp.client.generalClient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class GenericAccount {

    @Id
    private String accountId;
    private String accountType;
    private String accountUrl;
    private String accountIsoCurrencyCode;

    private Date accountInsertionDate;
    private String accountFk_insertionUser;
    private String accountInsertionTerminal;

}
