package com.nttdata.debitcard.service;

import com.nttdata.debitcard.model.DebitCardTransaction;

import reactor.core.publisher.Mono;

public interface DebitCardTransactionService {

	public Mono<DebitCardTransaction> save(DebitCardTransaction debitCardTransaction);
	
}
