package com.nttdata.customer.service;

import com.nttdata.customer.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

	  Flux<Customer> getAll();
	  Mono<Customer> save(Customer customer);
	  Mono<Customer> findById(String id);
	  Customer findByIdCacheable(String id);
	  Mono<Boolean> existsById(String id);
		Mono<Boolean> isCustomerVIP (String customerid);
		Mono<Boolean> isCustomerPYME(String customerid);
}
