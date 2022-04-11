package com.bootcamp.retailclient.repository;

import com.bootcamp.retailclient.model.RetailClient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RetailClientRepository extends ReactiveCrudRepository<RetailClient, String> {

    Flux<RetailClient> findByDocumentId(String num);

}
