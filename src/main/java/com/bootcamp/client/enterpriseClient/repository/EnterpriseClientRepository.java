package com.bootcamp.client.enterpriseClient.repository;

import com.bootcamp.client.enterpriseClient.entity.EnterpriseClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseClientRepository extends ReactiveMongoRepository<EnterpriseClient, String> {

}