package com.nttdata.product.service;

import java.util.Date;

import com.nttdata.product.model.Product;
import com.nttdata.product.model.Transaction;

import reactor.core.publisher.Mono;

public interface TransactionService {

	public Mono<Transaction> saveTransacionProduct(Product product, Integer monto, Date fecha);
	
}
