package com.bootcamp.retailclient.service;

import com.bootcamp.retailclient.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<Client> getAll();
    Flux<Client> getByNumeroDocumento(String num);
    Mono<Client> createClient(Client client);
}
