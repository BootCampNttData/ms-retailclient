package com.bootcamp.retailclient.controller;


import com.bootcamp.retailclient.model.RetailClient;
import com.bootcamp.retailclient.service.RetailClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/retailclnt")
@RequiredArgsConstructor
public class RetailClientController {
    public final RetailClientService service;

    @GetMapping
    public Flux<RetailClient> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{document}")
    public Flux<RetailClient> findByDocumentId(@PathVariable("document") String document){
        return service.getByDocumentId(document);
    }

    @PostMapping
    public Mono<RetailClient> create(@RequestBody RetailClient retailClient){
        return service.create(retailClient);
    }

    @PostMapping("/update")
    public Mono<RetailClient> update(@RequestBody RetailClient retailClient){
        return service.create(retailClient);
    }

    @DeleteMapping
    public Mono<RetailClient> delete(@RequestBody RetailClient retailClient){
        return service.delete(retailClient);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<RetailClient> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }

}
