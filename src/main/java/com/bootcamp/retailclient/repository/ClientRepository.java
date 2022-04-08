package com.bootcamp.retailclient.repository;

import com.bootcamp.retailclient.model.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ClientRepository extends ReactiveCrudRepository<Client, String> {

    Flux<Client> findByNumeroDocumento(String num);
}
