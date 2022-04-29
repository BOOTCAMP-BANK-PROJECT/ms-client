package com.bootcamp.client.personalClient;

import com.bootcamp.client.personalClient.dto.CreatePersonalClientAccountDto;
import com.bootcamp.client.personalClient.dto.CreatePersonalClientDto;
import com.bootcamp.client.personalClient.dto.DeletePersonalClientDto;
import com.bootcamp.client.personalClient.dto.UpdatePersonalClientDto;
import com.bootcamp.client.personalClient.entity.PersonalClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalClientService {

    public Flux<PersonalClient> getAll();

    public Mono<PersonalClient> getById(String id);

    public Mono<PersonalClient> getByDocumentNumber(String documentNumber);

    public Mono<PersonalClient> save(CreatePersonalClientDto o);

    public Mono<PersonalClient> addAccounts(CreatePersonalClientAccountDto o);

    public Mono<PersonalClient> update(UpdatePersonalClientDto o);

    public Mono<PersonalClient> delete(DeletePersonalClientDto o);
}