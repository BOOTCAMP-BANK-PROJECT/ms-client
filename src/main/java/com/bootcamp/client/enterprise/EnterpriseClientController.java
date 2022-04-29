package com.bootcamp.client.enterprise;

import com.bootcamp.client.enterprise.dto.CreateEnterpriseClientAccountDto;
import com.bootcamp.client.enterprise.dto.CreateEnterpriseClientDto;
import com.bootcamp.client.enterprise.dto.DeleteEnterpriseClientDto;
import com.bootcamp.client.enterprise.dto.UpdateEnterpriseClientDto;
import com.bootcamp.client.enterprise.entity.EnterpriseClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("client/enterprise")
@Tag(name = "Enterprise Clients Information", description = "Manage enterprise clients and minimal accounts information")
@CrossOrigin( value = { "*" })
@RequiredArgsConstructor
public class EnterpriseClientController {

    public final EnterpriseClientServiceImpl service;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Mono<EnterpriseClient>>> getById(@PathVariable String id) {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getById(id))
        );
    }

    @GetMapping("/find")
    public Mono<ResponseEntity<Mono<EnterpriseClient>>> getByRuc(@RequestParam(name="ruc") String ruc) {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getByRuc(ruc))
        );
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<EnterpriseClient>>> getAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getAll())
        );
    }

    @PostMapping
    public Mono<ResponseEntity<EnterpriseClient>> create(@RequestBody CreateEnterpriseClientDto o) {

        return service.save(o).map( p -> ResponseEntity
                .created(URI.create("/client/enterprise/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }

    @PostMapping(value = "/accounts")
    public Mono<ResponseEntity<EnterpriseClient>> addAccounts(@RequestBody CreateEnterpriseClientAccountDto o) {

        return service.addAccounts(o)
                .map(p -> ResponseEntity.created(URI.create("/client/enterprise/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping
    public Mono<ResponseEntity<EnterpriseClient>> update(@RequestBody UpdateEnterpriseClientDto o) {
        return service.update(o)
                .map(p -> ResponseEntity.created(URI.create("/client/enterprise/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<EnterpriseClient>> delete(@RequestBody DeleteEnterpriseClientDto o) {
        return service.delete(o)
                .map(p -> ResponseEntity.created(URI.create("/client/enterprise/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}