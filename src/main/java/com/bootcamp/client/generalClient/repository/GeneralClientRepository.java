package com.bootcamp.client.generalClient.repository;

import com.bootcamp.client.generalClient.entity.GeneralClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralClientRepository extends ReactiveMongoRepository<GeneralClient, String> {

}