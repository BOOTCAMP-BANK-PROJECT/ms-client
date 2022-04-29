package com.bootcamp.client.personalClient.dto;

import com.bootcamp.client.generalClient.entity.GenericAccount;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class DeletePersonalClientDto {

    /*@Id
    private String id;*/

    //private String documentType;
    private String documentNumber;

}