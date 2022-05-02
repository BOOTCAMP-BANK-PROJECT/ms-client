package com.bootcamp.client.personal;

import com.bootcamp.client.general.entity.ClientProfiles;
import com.bootcamp.client.personal.dto.*;
import com.bootcamp.client.personal.entity.PersonalClient;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("client/personal")
@Tag(name = "Personal Clients Information", description = "Manage personal clients and minimal accounts information")
@CrossOrigin( value = { "*" })
@RequiredArgsConstructor
public class PersonalClientController {

    public final PersonalClientServiceImpl service;

    @GetMapping("/{documentNumber}")
    public Mono<ResponseEntity<Mono<PersonalClient>>> getByDocumentNumber(@PathVariable String documentNumber) {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getByDocumentNumber(documentNumber))
        );
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<PersonalClient>>> getAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getAll())
        );
    }

    @GetMapping("/profiles")
    public Mono<ResponseEntity<Mono<String>>> getProfiles() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(new Gson().toJson(ClientProfiles.getPersonalProfiles())))
        );
    }

    @PostMapping
    public Mono<ResponseEntity<PersonalClient>> create(@RequestBody CreatePersonalClientDto o) {

        return service.save(o).map( p -> ResponseEntity
                .created(URI.create("/client/personal/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }

    @PostMapping(value = "/{documentNumber}/accounts")
    public Mono<ResponseEntity<PersonalClient>> addAccounts(@PathVariable String documentNumber, @RequestBody CreatePersonalClientAccountDto o) {

        return service.addAccount(documentNumber, o)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(documentNumber)
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{documentNumber}/accounts")
    public Mono<ResponseEntity<PersonalClient>> updateAccounts(@PathVariable String documentNumber, @RequestBody UpdatePersonalClientAccountDto o) {

        return service.updateAccount(documentNumber, o)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(documentNumber)
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{documentNumber}/accounts/{accountId}")
    public Mono<ResponseEntity<PersonalClient>> deleteAccounts(@PathVariable String documentNumber, @PathVariable String accountId) {

        return service.deleteAccount(documentNumber, accountId)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(documentNumber)
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping
    public Mono<ResponseEntity<PersonalClient>> update(@RequestBody UpdatePersonalClientDto o) {
        return service.update(o)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<PersonalClient>> delete(@RequestBody DeletePersonalClientDto o) {
        return service.delete(o)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}