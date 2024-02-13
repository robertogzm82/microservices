package com.nttdata.debitcard.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.debitcard.model.DebitCard;
import com.nttdata.debitcard.model.DebitCardTransaction;
import com.nttdata.debitcard.model.Transaction;
import com.nttdata.debitcard.repository.DebitCardRepository;
import com.nttdata.debitcard.repository.DebitCardTransactionRepository;
import com.nttdata.debitcard.service.DebitCardService;
import com.nttdata.debitcard.service.DebitCardTransactionService;
import com.nttdata.debitcard.service.TransactionService;

import reactor.core.publisher.Mono;

@Service
public class DebitCardTransactionServiceImpl implements DebitCardTransactionService {
  
	@Autowired
	private DebitCardTransactionRepository debitCardTransactionRepository;
	
	@Override
	public Mono<DebitCardTransaction> save(DebitCardTransaction debitCardTransaction) {
		return debitCardTransactionRepository.save(debitCardTransaction);
	}





}
