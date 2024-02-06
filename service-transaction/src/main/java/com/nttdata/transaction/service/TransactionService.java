package com.nttdata.transaction.service;

import com.nttdata.transaction.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

	  Flux<Transaction> getAll();
	  Mono<?> save(Transaction transaction);

}
