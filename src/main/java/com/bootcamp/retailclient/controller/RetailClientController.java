package com.bootcamp.retailclient.controller;

import com.bootcamp.retailclient.model.ProductsReport;
import com.bootcamp.retailclient.model.RetailClient;
import com.bootcamp.retailclient.service.RetailClientService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/retailclient")
@RequiredArgsConstructor
public class RetailClientController {
    @Value("${spring.cloud.gateway.url}")
    private String gtWaySrv;

    public final RetailClientService service;
    Logger logger = LoggerFactory.getLogger(RetailClientController.class);

    @GetMapping
    public Flux<RetailClient> getAll(){
        logger.info("***********  Test *************");
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

    @GetMapping("/getProducts/{idClient}")
    public Flux<ProductsReport> getProducts(@PathVariable("idClient") String idClient){
        ProductsReport report = new ProductsReport();
        WebClient  webClient = WebClient.create(gtWaySrv);
//        var savingAccounts = Mono.from(Mono.from(webClient.get()
        List<Integer> savAccLst=new ArrayList<>();
        logger.info("Saving Accounts");
        webClient.get()
                 .uri("/savingaccount/findAcountsByClientId/{id}",idClient)
                 .retrieve()
                 .bodyToFlux(Integer.class)
                 .subscribe(savAccLst::add);

        logger.info(savAccLst.toString());
        report.setSavingAccountList(savAccLst);
        return Flux.just(report);
    }

}
