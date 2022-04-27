package com.bootcamp.client.enterpriseClient;

import com.bootcamp.client.enterpriseClient.dto.CreateEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.dto.DeleteEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.dto.UpdateEnterpriseClientDto;
import com.bootcamp.client.enterpriseClient.entity.EnterpriseClient;
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

    @GetMapping//(value = "/fully")
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