package com.bootcamp.retailclient.service;

import com.bootcamp.retailclient.model.RetailClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RetailClientService {
    Flux<RetailClient> findAll();
    Flux<RetailClient> getByDocumentId(String num);
    Mono<RetailClient> create(RetailClient retailClient);
    Mono<RetailClient> update(RetailClient retailClient);
    Mono<RetailClient> deleteById(String id);
    Mono delete(RetailClient retailClient);
}
