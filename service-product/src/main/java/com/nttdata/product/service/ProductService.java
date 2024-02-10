package com.nttdata.product.service;

import java.util.Date;

import com.nttdata.product.model.Product;
import com.nttdata.product.model.ProductType;
import com.nttdata.product.model.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	  Flux<Product> getAll();
	  Mono<?> save(Product product);
	  Mono<Product> findById(String id);
	  Mono<Boolean> existsById(String id);
	  Flux<Product> findByCustomerId(String id);
		Mono<Long> countByCustomerId(String customerId);
		Mono<Long> countByCustomerIdAndTipo(String customerId, String tipo);
		Mono<Transaction> saveInitialAmount(Product product, Integer monto, Date date);
}
