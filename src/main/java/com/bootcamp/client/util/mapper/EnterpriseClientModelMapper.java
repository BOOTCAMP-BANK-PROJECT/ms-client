package com.bootcamp.client.util.mapper;

import com.bootcamp.client.enterpriseClient.dto.CreateEnterpriseClientAccountDto;
import com.bootcamp.client.enterpriseClient.dto.CreateEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.dto.DeleteEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.dto.UpdateEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.entity.EnterpriseClient;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class EnterpriseClientModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static EnterpriseClientModelMapper instance;

    private EnterpriseClientModelMapper() { }

    public static EnterpriseClientModelMapper singleInstance() {
        if ( instance == null ) {
            instance = new EnterpriseClientModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public EnterpriseClient reverseMapCreateWithDate(CreateEnterpriseClientDto createDto) {
        EnterpriseClient o = mapper.map(createDto, EnterpriseClient.class);

        o.setInsertionDate(new Date());
        o.setRegistrationStatus((short) 1);

        return o;
    }

    public EnterpriseClient reverseMapUpdateAddAccounts(EnterpriseClient p, CreateEnterpriseClientAccountDto o) {

        p.getAccounts().addAll(o.getAccounts());

        return p;

    }

    public EnterpriseClient reverseMapUpdate(EnterpriseClient p, UpdateEnterpriseClientDto updateDto) {

        p.setAccounts(updateDto.getAccounts());
        p.setLegalResidence(updateDto.getLegalResidence());

        return p;
    }

    public EnterpriseClient reverseMapDelete(EnterpriseClient p, DeleteEnterpriseClientDto deleteDto) {

        p.setRegistrationStatus((short) 0);

        return p;
    }

}