package com.nttdata.debitcard.service;

import java.util.Date;

import com.nttdata.debitcard.model.DebitCard;
import com.nttdata.debitcard.model.Product;
import com.nttdata.debitcard.model.Transaction;

import reactor.core.publisher.Mono;

public interface TransactionService {

	Mono<Integer> getSaldo(String id);
	
	Integer getSaldoNR(String productid) ;
	
	Mono<Transaction> saveTransacionProduct(Product product, Integer monto, Date fecha);

	Mono<Transaction> save(Transaction transaction);

	String registrarPago(DebitCard debitCard, String mainproductid, Integer monto);

	boolean isRetiroPossible(String debitcardId, Integer monto);

	String findProductRetiroDebitCard(DebitCard debitCard, Integer monto);
	
}
