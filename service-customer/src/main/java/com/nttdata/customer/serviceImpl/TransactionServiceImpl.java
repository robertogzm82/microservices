package com.nttdata.customer.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.customer.service.ProductService;
import com.nttdata.customer.service.TransactionService;

import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

	private WebClient webClient;
	
	public TransactionServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8084").build();
    }

	@Override
	public Mono<Integer> getSaldo(String productid) {
		return  webClient.get()
				.uri("/transaction/balance/"+productid)
				.retrieve()
				.bodyToMono(Integer.class);
	}
	
}
