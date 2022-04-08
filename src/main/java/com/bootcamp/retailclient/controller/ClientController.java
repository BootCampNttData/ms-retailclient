package com.bootcamp.retailclient.controller;


import com.bootcamp.retailclient.model.Client;
import com.bootcamp.retailclient.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    public final ClientService service;

    @GetMapping
    public Flux<Client> getAll(){
        return service.getAll();
    }

    @GetMapping("/find/{num}")
    public Flux<Client> getList(@PathVariable("num") String num){
        return service.getByNumeroDocumento(num);
    }


    @PostMapping
    public Mono<Client> createClient(@RequestBody Client client){
        return service.createClient(client);
    }
}
