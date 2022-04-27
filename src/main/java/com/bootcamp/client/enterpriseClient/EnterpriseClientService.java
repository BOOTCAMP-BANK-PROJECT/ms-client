package com.bootcamp.client.enterpriseClient;

import com.bootcamp.client.enterpriseClient.dto.CreateEnterpriseClientAccountDto;
import com.bootcamp.client.enterpriseClient.dto.CreateEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.dto.DeleteEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.dto.UpdateEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.entity.EnterpriseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnterpriseClientService {

    public Flux<EnterpriseClient> getAll();

    public Mono<EnterpriseClient> getById(String id);

    public Mono<EnterpriseClient> getByRuc(String ruc);

    public Mono<EnterpriseClient> save(CreateEnterpriseClientDto o);

    public Mono<EnterpriseClient> addAccounts(CreateEnterpriseClientAccountDto o);

    public Mono<EnterpriseClient> update(UpdateEnterpriseClientDto o);

    public Mono<EnterpriseClient> delete(DeleteEnterpriseClientDto o);
}
