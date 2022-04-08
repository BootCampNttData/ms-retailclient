package com.bootcamp.retailclient.service.Impl;

import com.bootcamp.retailclient.model.Client;
import com.bootcamp.retailclient.repository.ClientRepository;
import com.bootcamp.retailclient.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    public final ClientRepository repository;
    @Override
    public Flux<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Client> createClient(Client client) {
        return repository.save(client);
    }

    @Override
    public Flux<Client> getByNumeroDocumento(String num) {
        return repository.findByNumeroDocumento(num);
    }

}
