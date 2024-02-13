package com.nttdata.debitcard.service;

import java.util.Date;

import com.nttdata.debitcard.model.Product;
import com.nttdata.debitcard.model.Transaction;

import reactor.core.publisher.Mono;

public interface TransactionService {

	Mono<Integer> getSaldo(String id);

	Mono<Transaction> saveTransacionProduct(Product product, Integer monto, Date fecha);

	Mono<Transaction> save(Transaction transaction);
	
}
