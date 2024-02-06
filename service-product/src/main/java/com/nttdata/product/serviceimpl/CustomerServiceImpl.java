package com.nttdata.product.serviceimpl;

import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.product.model.Customer;
import com.nttdata.product.model.ProductDto;
import com.nttdata.product.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

	private WebClient webClient;
	
	public CustomerServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }
	
	@Override
	public Flux<Customer> getAll() {
		return webClient.get()
				.uri("/customer")
				.retrieve()
				.bodyToFlux(Customer.class);
	}

	@Override
	public Mono<Customer> findById(String id) {
		return webClient.get()
				.uri("/customer/"+id)
				.retrieve()
				.bodyToMono(Customer.class);
	}

	@Override
	public Mono<Boolean> existsById(String id) {
		// TODO Auto-generated method stub
		return webClient.get()
				.uri("/customer/exist/"+id)
				.retrieve()
				.bodyToMono(Boolean.class);
	}
	 
}
