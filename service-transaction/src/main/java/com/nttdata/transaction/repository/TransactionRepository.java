package com.nttdata.transaction.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.transaction.model.Transaction;

import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.Month;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository <Transaction, String>{

	public Flux<Transaction> findByProductId(String productId);
	
	public Flux<Transaction> findByCustomerId(String customerid);
	
	@Query(" {'date':{ $gte:?0 , $lt: ?1 } , 'productId' : ?2 }")
	public Flux<Transaction> findByDateBetween(LocalDate start, LocalDate end, String productid);
	
}
