package com.bootcamp.client.enterpriseClient;

import com.bootcamp.client.enterpriseClient.dto.CreateEnterpriseClientAccountDto;
import com.bootcamp.client.enterpriseClient.dto.CreateEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.dto.DeleteEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.dto.UpdateEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.entity.EnterpriseClient;
import com.bootcamp.client.enterpriseClient.repository.EnterpriseClientRepository;
import com.bootcamp.client.util.Util;
import com.bootcamp.client.util.handler.exceptions.BadRequestException;
import com.bootcamp.client.util.mapper.EnterpriseClientModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EnterpriseClientServiceImpl implements EnterpriseClientService {

    static EnterpriseClientModelMapper modelMapper = EnterpriseClientModelMapper.singleInstance();

    public final EnterpriseClientRepository repository;

    @Override
    public Flux<EnterpriseClient> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<EnterpriseClient> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<EnterpriseClient> getByRuc(String ruc) {
        return repository.findByRuc(ruc);
    }

    @Override
    public Mono<EnterpriseClient> save(CreateEnterpriseClientDto o) {

        return repository.findByRuc(o.getRuc())
                .map( p -> {
                    throw new BadRequestException(
                                "RUC",
                                "[save] The RUC number "+o.getRuc()+ " is already in use.",
                                "An error occurred while trying to create an item.",
                                getClass(),
                                "save"
                        );
                } )
                .switchIfEmpty(Mono.defer(() -> {

                    Util.verifyRuc(o.getRuc(), o.getRuc(), getClass(), "save.verifyRuc");

                    o.getAccounts().forEach( acc -> Util.verifyCurrency(acc, getClass()));

                    return repository.save(modelMapper.reverseMapCreateWithDate(o));

                }))
                .onErrorResume( e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                )))
                .cast(EnterpriseClient.class);


    }

    @Override
    public Mono<EnterpriseClient> addAccounts(CreateEnterpriseClientAccountDto o) {

        return repository.findById(o.getId())
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + o.getId() + " was not found. >> switchIfEmpty")))
                .flatMap( p -> {

                    Util.verifyRuc(o.getRuc(), p.getRuc(), getClass(),"addAccounts.flatMap");

                    o.getAccounts().forEach( acc -> Util.verifyCurrency(acc, getClass()));

                    return repository.save(modelMapper.reverseMapUpdateAddAccounts(p, o));
                } )
                .onErrorResume( e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }

    @Override
    public Mono<EnterpriseClient> update(UpdateEnterpriseClientDto o) {

        return repository.findById(o.getId())
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + o.getId() + " was not found. >> switchIfEmpty")))
                .flatMap( p -> {

                    Util.verifyRuc(o.getRuc(), p.getRuc(), getClass(),"update.flatMap");

                    o.getAccounts().forEach( acc -> Util.verifyCurrency(acc, getClass()));

                    return repository.save(modelMapper.reverseMapUpdate(p, o));
                } )
                .onErrorResume( e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }

    @Override
    public Mono<EnterpriseClient> delete(DeleteEnterpriseClientDto o) {

        return repository.findById(o.getId())
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + o.getId() + " was not found. >> switchIfEmpty")))
                .flatMap( p -> {

                    if( !p.getRuc().equals(o.getRuc()) ) {
                        return Mono.error(new Exception("An item with the RUC " + o.getRuc() + " was not found. >> onFlatMap"));
                    }

                    return repository.save(modelMapper.reverseMapDelete(p, o));

                } )
                .onErrorResume( e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }
}