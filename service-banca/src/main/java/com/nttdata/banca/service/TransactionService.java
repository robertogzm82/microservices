package com.nttdata.banca.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

	Mono<Integer> getMonoSaldoF (String productid);
	Flux<Integer> getFluxSaldoF (String productid);
}
