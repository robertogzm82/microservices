package com.nttdata.product.service;

import com.nttdata.product.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

	  Flux<Customer> getAll();
	  Mono<Customer> findById(String id);
	  Mono<Boolean> existsById(String id);
	  
}
