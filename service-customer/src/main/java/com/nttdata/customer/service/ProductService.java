package com.nttdata.customer.service;

import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<Boolean> existsById(String id);
	
}
