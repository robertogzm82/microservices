package com.nttdata.customer.service;

import reactor.core.publisher.Mono;

import com.nttdata.customer.model.Product;

import reactor.core.publisher.Flux;

public interface ProductService {

	Mono<Boolean> existsById(String id);
	Flux<Product> findByCustomerId(String customerId);
	Mono<Integer> countByCustomerIdAndTipo(String customerid, String string);
	Mono<Product> findById(String id);
	
}
