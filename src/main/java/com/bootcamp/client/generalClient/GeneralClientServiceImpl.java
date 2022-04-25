package com.bootcamp.client.generalClient;

import com.bootcamp.client.generalClient.dto.CreateGeneralClientDto;
import com.bootcamp.client.generalClient.dto.UpdateGeneralClientDto;
import com.bootcamp.client.generalClient.entity.GeneralClient;
import com.bootcamp.client.generalClient.repository.GeneralClientRepository;
import com.bootcamp.client.util.Util;
import com.bootcamp.client.util.handler.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GeneralClientServiceImpl implements GeneralClientService {

    public final GeneralClientRepository repository;

    @Override
    public Flux<GeneralClient> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<GeneralClient> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<GeneralClient> save(CreateGeneralClientDto o) {

        if(!Util.isValidCurrency(o.getIsoCurrencyCode())){
            throw new BadRequestException(
                    "CURRENCY",
                    "An error occurred while trying to create an item.",
                    o.getIsoCurrencyCode() + " is an invalid currency code.",
                    GeneralClientServiceImpl.class,
                    "save"
            );
        }

        return repository.save(Util.mapCreate(o));
    }

    @Override
    public Mono<GeneralClient> update(UpdateGeneralClientDto o) {

        return repository.findById(o.getId())
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + o.getId() + " was not found. >> switchIfEmpty")))
                .flatMap( p -> repository.save(Util.mapUpdate(p, o)) )
                .onErrorResume( e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        GeneralClientServiceImpl.class,
                        "update.onErrorResume"
                )));
    }

    @Override
    public Mono<GeneralClient> delete(String id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + id + " was not found. >> switchIfEmpty")))
                .flatMap( p -> repository.save(Util.mapDelete(p, id)) )
                .onErrorResume( e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        GeneralClientServiceImpl.class,
                        "update.onErrorResume"
                )));
    }
}