package com.bootcamp.retailclient.repository;

import com.bootcamp.retailclient.model.RetailClient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RetailClientRepository extends ReactiveCrudRepository<RetailClient, String> {

    Mono<RetailClient> findByDocumentId(String num);

}
