package com.nttdata.customer.service;

import reactor.core.publisher.Mono;

public interface TransactionService {

	Mono<Integer> getSaldo(String id);
	
}
