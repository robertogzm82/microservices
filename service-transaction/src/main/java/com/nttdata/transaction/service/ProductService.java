package com.nttdata.transaction.service;

import com.nttdata.transaction.model.Product;
import com.nttdata.transaction.model.Transaction;

import reactor.core.publisher.Mono;

public interface ProductService {

	  Mono<Product> findById(String id);

		Mono<String> getTipoProductoTransaction(Transaction transaction);

}
