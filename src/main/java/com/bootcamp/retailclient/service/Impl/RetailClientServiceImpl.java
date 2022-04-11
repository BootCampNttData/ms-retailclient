package com.bootcamp.retailclient.service.Impl;

import com.bootcamp.retailclient.model.RetailClient;
import com.bootcamp.retailclient.repository.RetailClientRepository;
import com.bootcamp.retailclient.service.RetailClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RetailClientServiceImpl implements RetailClientService {
    public final RetailClientRepository repository;

    @Override
    public Mono<RetailClient> create(RetailClient retailClient) {
        return repository.save(retailClient);
    }

    @Override
    public Mono<RetailClient> update(RetailClient retailClient) {
        return repository.save(retailClient);
    }

    @Override
    public Mono deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono delete(RetailClient retailClient) {
        return repository.delete(retailClient);
    }

    @Override
    public Flux<RetailClient> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<RetailClient> getByDocumentId(String num) {
        return repository.findByDocumentId(num);
    }

}
