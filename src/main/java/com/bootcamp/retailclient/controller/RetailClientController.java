package com.bootcamp.retailclient.controller;

import com.bootcamp.retailclient.exception.RetailClientValidationException;
import com.bootcamp.retailclient.model.ProductsReport;
import com.bootcamp.retailclient.model.RetailClient;
import com.bootcamp.retailclient.service.RetailClientService;
import com.bootcamp.retailclient.util.Constants;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


@RestController
@RequestMapping("/retailclient")
@RequiredArgsConstructor
public class RetailClientController {
    private final Constants constants;
    public final RetailClientService service;
    Logger logger = LoggerFactory.getLogger(RetailClientController.class);

    @GetMapping
    public Flux<RetailClient> getAll(){
        logger.info("***********  Test *************");
        return service.findAll();
    }

    @GetMapping("/find/{document}")
    public Mono<RetailClient> findByDocumentId(@PathVariable("document") String document){
        return service.getByDocumentId(document);
    }

    @PostMapping
    public Mono<RetailClient> create(@RequestBody RetailClient retailClient){
        return Mono.just(retailClient)
                .then(check(retailClient, sav -> Optional.of(sav).isEmpty(), "Retail Client has not data.!!!"))
                .then(check(retailClient, sav -> ObjectUtils.isEmpty(sav.getDocumentId()), "Client Id is required"))
                .then(findByDocumentId(retailClient.getDocumentId())
                        .<RetailClient>handle((record, sink) -> sink.error(new RetailClientValidationException("The Client already Exist")))
                        .switchIfEmpty(service.create(retailClient)))
                ;

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

    @GetMapping("/getProducts/{idClient}")
    public Flux<ProductsReport> getProducts(@PathVariable("idClient") String idClient){
        WebClient  webClient = WebClient.create(constants.getGatewayUrl());
        logger.info("Saving Accounts");
        List<Integer> savAccLst=new ArrayList<>();

        var retailClients = webClient.get()
                .uri("/savingaccount/findAcountByClientId/{id}",idClient)
                .retrieve().bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Saving Account"));

        var currentAccounts = webClient.get()
                .uri("/currentaccount/findAcountsByClientRuc/{id}",idClient)
                .retrieve()
                .bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Current Accounts"));

        var fixedDepositAccounts = webClient.get()
                .uri("/fixeddepositaccount/findAcountsByClientId/{id}",idClient)
                .retrieve()
                .bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Fixed Deposit Account"));

        var creditCards = webClient.get()
                .uri("/creditcard/findCreditCardByClientId/{id}",idClient)
                .retrieve()
                .bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Credit Cards"));

        var credits = webClient.get()
                .uri("/credit/findCreditByClientId/{id}",idClient)
                .retrieve()
                .bodyToFlux(String.class)
                .map(nc -> new ProductsReport(nc,"Credits"));

        return Flux.merge(retailClients,currentAccounts,fixedDepositAccounts,creditCards,credits);
    }

    private <T> Mono<Void> check(T retailClient, Predicate<T> predicate, String messageForException) {
        return Mono.create(sink -> {
            if (predicate.test(retailClient)) {
                sink.error(new Exception(messageForException));
                return;
            } else {
                sink.success();
            }
        });
    }

}
