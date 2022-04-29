package com.bootcamp.client.util.mapper;

import com.bootcamp.client.personalClient.dto.CreatePersonalClientAccountDto;
import com.bootcamp.client.personalClient.dto.CreatePersonalClientDto;
import com.bootcamp.client.personalClient.dto.DeletePersonalClientDto;
import com.bootcamp.client.personalClient.dto.UpdatePersonalClientDto;
import com.bootcamp.client.personalClient.entity.PersonalClient;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class PersonalClientModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static PersonalClientModelMapper instance;

    private PersonalClientModelMapper() { }

    public static PersonalClientModelMapper singleInstance() {
        if ( instance == null ) {
            instance = new PersonalClientModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public PersonalClient reverseMapCreateWithDate(CreatePersonalClientDto createDto) {
        PersonalClient o = mapper.map(createDto, PersonalClient.class);

        o.setInsertionDate(new Date());
        o.setRegistrationStatus((short) 1);

        return o;
    }

    public PersonalClient reverseMapUpdateAddAccounts(PersonalClient p, CreatePersonalClientAccountDto o) {

        p.getAccounts().addAll(o.getAccounts());

        return p;

    }

    public PersonalClient reverseMapUpdate(PersonalClient p, UpdatePersonalClientDto updateDto) {

        p.setFirstName(updateDto.getFirstName());
        p.setLastName(updateDto.getLastName());
        p.setResidenceAddress(updateDto.getResidenceAddress());

        return p;
    }

    public PersonalClient reverseMapDelete(PersonalClient p, DeletePersonalClientDto deleteDto) {

        p.setRegistrationStatus((short) 0);

        return p;
    }

}