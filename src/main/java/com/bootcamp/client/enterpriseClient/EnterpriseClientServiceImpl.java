package com.bootcamp.client.enterpriseClient;

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

import java.util.Arrays;
import java.util.logging.Level;

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
    public Mono<EnterpriseClient> save(CreateEnterpriseClientDto o) {

        o.getAccounts().forEach( acc -> {
            if(!Util.isValidCurrency(acc.getAccountIsoCurrencyCode())){
                throw new BadRequestException(
                        "CURRENCY",
                        "An error occurred while trying to create an item.",
                        "["+acc.getAccountId()+"] "+acc.getAccountIsoCurrencyCode() + " is an invalid currency code.",
                        getClass(),
                        "save"
                );
            }
        });

        return repository.save(modelMapper.reverseMapCreateWithDate(o));
    }

    @Override
    public Mono<EnterpriseClient> update(UpdateEnterpriseClientDto o) {

        return repository.findById(o.getId())
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + o.getId() + " was not found. >> switchIfEmpty")))
                .flatMap( p -> {

                    o.getAccounts().forEach( acc -> {
                        if(!Util.isValidCurrency(acc.getAccountIsoCurrencyCode())){
                            throw new BadRequestException(
                                    "CURRENCY",
                                    "["+acc.getAccountId()+"] "+acc.getAccountIsoCurrencyCode() + " is an invalid currency code.",
                                    "",
                                    getClass(),
                                    "update"
                            );
                        }
                    });

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

                    if(p.getRuc() == o.getRuc()) {
                        return repository.save(modelMapper.reverseMapDelete(o));
                    }
                    else {
                        return Mono.error(new Exception("An item with the RUC " + o.getRuc() + " was not found. >> onFlatMap"));
                    }

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