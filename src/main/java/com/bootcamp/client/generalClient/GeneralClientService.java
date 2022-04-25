package com.bootcamp.client.generalClient;

import com.bootcamp.client.generalClient.dto.CreateGeneralClientDto;
import com.bootcamp.client.generalClient.dto.UpdateGeneralClientDto;
import com.bootcamp.client.generalClient.entity.GeneralClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GeneralClientService {

    public Flux<GeneralClient> getAll();

    public Mono<GeneralClient> getById(String id);

    public Mono<GeneralClient> save(CreateGeneralClientDto o);

    public Mono<GeneralClient> update(UpdateGeneralClientDto o);

    public Mono<GeneralClient> delete(String id);
}
