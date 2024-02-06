package com.nttdata.customer.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.customer.service.ProductService;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	private WebClient webClient;
	
	public ProductServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }
	
	@Override
	public Mono<Boolean> existsById(String id) {
		return  webClient.get()
				.uri("/product/exist/"+id)
				.retrieve()
				.bodyToMono(Boolean.class);
	}
	
}
