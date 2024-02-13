package com.nttdata.banca.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.banca.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

	private WebClient webClient;
	
	public TransactionServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8084").build();
    }

	@Override
	public Mono<Integer> getMonoSaldoF(String productid) {
		return webClient
		.get()
		.uri("/transaction/balance/" + productid)  
		.retrieve()
		.bodyToMono(Integer.class);
	}
	
	@Override
	public Flux<Integer> getFluxSaldoF(String productid) {
		return webClient
		.get()
		.uri("/transaction/balance/" + productid)  
		.retrieve()
		.bodyToFlux(Integer.class);
	}
	
}
