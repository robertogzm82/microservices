package com.nttdata.transaction.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.transaction.model.Transaction;
import com.nttdata.transaction.repository.TransactionRepository;
import com.nttdata.transaction.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Flux<Transaction> getAll() {
		return transactionRepository.findAll();
	}

	@Override
	public Mono<Transaction> save(Transaction transaction) {
		return  transactionRepository.save(transaction);
	}

	
}
