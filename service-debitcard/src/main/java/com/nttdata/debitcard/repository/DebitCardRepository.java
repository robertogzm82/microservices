package com.nttdata.debitcard.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.debitcard.model.DebitCard;

@Repository
public interface DebitCardRepository extends ReactiveMongoRepository <DebitCard, String>{

}
