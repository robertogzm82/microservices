package com.nttdata.product.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.product.model.Product;
import com.nttdata.product.model.ProductType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository <Product, String>{

	Flux<Product> findByCustomerId (String customerId);
	
	Mono<Long> countByCustomerId(String customerId);
	
	Mono<Long> countByCustomerIdAndTipo(String customerId, ProductType tipo);
	
}
