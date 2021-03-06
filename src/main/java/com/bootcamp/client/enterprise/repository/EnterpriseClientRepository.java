package com.bootcamp.client.enterprise.repository;

import com.bootcamp.client.enterprise.entity.EnterpriseClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EnterpriseClientRepository extends ReactiveMongoRepository<EnterpriseClient, String> {

    Mono<EnterpriseClient> findByRuc(String ruc);

    Mono<Boolean> existsByRuc(String ruc);

}