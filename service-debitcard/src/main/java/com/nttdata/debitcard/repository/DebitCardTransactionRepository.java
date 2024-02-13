package com.nttdata.debitcard.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.debitcard.model.DebitCardTransaction;

@Repository
public interface DebitCardTransactionRepository extends ReactiveMongoRepository <DebitCardTransaction, String>{
	
}
