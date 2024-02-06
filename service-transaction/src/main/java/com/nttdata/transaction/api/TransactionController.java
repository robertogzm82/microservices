package com.nttdata.transaction.api;

import java.util.ArrayList;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.transaction.model.Transaction;
import com.nttdata.transaction.service.TransactionService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class TransactionController {
 
	@Autowired
	private TransactionService transactionrService;

	@PostMapping(value = "/transaction")
	public Mono<?> register(@RequestBody Transaction transaction) {
		return isvalid(transaction)
		.flatMap( p -> { if(p) {
											log.info("terminó ejecución funcion isvalid");
											return transactionrService.save(transaction);
										}	else
											return Mono.error(new RuntimeException("El producto no es válido"));
									 }  );
	}
	
	private Mono<Boolean> isvalid(Transaction transaction) {
		return Mono.just(true);
	}

	@GetMapping(value = "/transaction")
	public Flux<Transaction> getAll() {
	 	return transactionrService.getAll();
	}
	
}
