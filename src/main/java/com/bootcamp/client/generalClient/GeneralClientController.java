package com.bootcamp.client.generalClient;

import com.bootcamp.client.generalClient.dto.CreateGeneralClientDto;
import com.bootcamp.client.generalClient.dto.UpdateGeneralClientDto;
import com.bootcamp.client.generalClient.entity.GeneralClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("client/info")
@Tag(name = "Clients Information", description = "Manage Clients and minimal accounts information")
@CrossOrigin( value = { "*" })
@RequiredArgsConstructor
public class GeneralClientController {

    public final GeneralClientServiceImpl service;

    @GetMapping//(value = "/fully")
    public Mono<ResponseEntity<Flux<GeneralClient>>> getAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getAll())
        );
    }

    @PostMapping
    public Mono<ResponseEntity<GeneralClient>> create(@RequestBody CreateGeneralClientDto o) {

        return service.save(o).map( p -> ResponseEntity
                .created(URI.create("/SavingAccount/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }

    @PutMapping
    public Mono<ResponseEntity<GeneralClient>> update(@RequestBody UpdateGeneralClientDto o) {
        return service.update(o)
                .map(p -> ResponseEntity.created(URI.create("/SavingAccount/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<GeneralClient>> delete(@RequestBody String id) {
        return service.delete(id)
                .map(p -> ResponseEntity.created(URI.create("/SavingAccount/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}