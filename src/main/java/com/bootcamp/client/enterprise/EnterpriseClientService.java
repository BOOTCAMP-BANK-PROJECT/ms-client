package com.bootcamp.client.enterprise;

import com.bootcamp.client.enterprise.dto.CreateEnterpriseClientAccountDto;
import com.bootcamp.client.enterprise.dto.CreateEnterpriseClientDto;
import com.bootcamp.client.enterprise.dto.DeleteEnterpriseClientDto;
import com.bootcamp.client.enterprise.dto.UpdateEnterpriseClientDto;
import com.bootcamp.client.enterprise.entity.EnterpriseClient;
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
