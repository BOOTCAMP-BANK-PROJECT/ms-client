package com.bootcamp.client.personal;

import com.bootcamp.client.personal.dto.CreatePersonalClientAccountDto;
import com.bootcamp.client.personal.dto.CreatePersonalClientDto;
import com.bootcamp.client.personal.dto.DeletePersonalClientDto;
import com.bootcamp.client.personal.dto.UpdatePersonalClientDto;
import com.bootcamp.client.personal.entity.PersonalClient;
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

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Mono<PersonalClient>>> getById(@PathVariable String id) {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getById(id))
        );
    }

    @GetMapping("/find")
    public Mono<ResponseEntity<Mono<PersonalClient>>> getByRuc(@RequestParam(name="documentNumber") String ruc) {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getByDocumentNumber(ruc))
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

    @PostMapping
    public Mono<ResponseEntity<PersonalClient>> create(@RequestBody CreatePersonalClientDto o) {

        return service.save(o).map( p -> ResponseEntity
                .created(URI.create("/client/personal/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }

    @PostMapping(value = "/accounts")
    public Mono<ResponseEntity<PersonalClient>> addAccounts(@RequestBody CreatePersonalClientAccountDto o) {

        return service.addAccounts(o)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(p.getId())
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