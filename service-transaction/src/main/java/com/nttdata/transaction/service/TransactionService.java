package com.nttdata.transaction.service;

import java.time.LocalDate;

import com.nttdata.transaction.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

	  Flux<Transaction> getAll();
	  Mono<?> save(Transaction transaction);
		Mono<Integer> getSaldoF (String productid);
		Flux<Transaction> findByCustomerId(String customerid);
		Flux<Transaction> findByProductId(String productid);
		Mono<Boolean> iscuentaBancaria(Transaction transaction);
		Mono<Transaction> save_transaction_comision(Transaction transaction);
		Flux<Transaction> findByDateBetween(LocalDate start, LocalDate end,String productid);
		Mono<Transaction> transfer(String productid_ini, String product_fin , int monto );

}
