package com.nttdata.debitcard.service;

import com.nttdata.debitcard.model.DebitCard;
import com.nttdata.debitcard.model.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {

	public Mono<DebitCard> save (DebitCard debitCard);
	public Mono<DebitCard> retiro(DebitCard debitCard);
	public Mono<String> pago(Transaction transaction, String debitCardId);
	public Mono<DebitCard> findByid (String id);
	public Flux<String> getProductsId (DebitCard debitCard, Integer saldo);
	
}
