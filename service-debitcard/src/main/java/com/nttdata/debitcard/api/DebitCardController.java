package com.nttdata.debitcard.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.debitcard.model.DebitCard;
import com.nttdata.debitcard.model.Transaction;
import com.nttdata.debitcard.service.DebitCardService;
import com.nttdata.debitcard.service.DebitCardTransactionService;
import com.nttdata.debitcard.service.TransactionService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class DebitCardController {
	
	@Autowired
	private DebitCardService debitCardService;
	
	@Autowired
	private DebitCardTransactionService debitCardTransactionService;
	
	@Autowired
	private TransactionService transactionService;
	
	
	@PostMapping(value="/debitCard/pago/{debitcardId}/{monto}")
	public Mono<String> pago (@PathVariable String monto, @PathVariable String debitcardId){
		return debitCardService.findByid(debitcardId)
				.map( debitCard -> { return new Transaction(debitCard,"pago", Integer.valueOf(monto) ); }  )
				.flatMap( t -> debitCardService.pago(t, debitcardId) );
	}
	
	@PostMapping(value="/debitCard/retiro/{debitcardId}/{monto}")
	public Mono<String> retiro (@PathVariable Integer monto, @PathVariable String debitcardId){
		return debitCardService.findByid(debitcardId)
				.map( debitCard -> { if ( transactionService.getSaldoNR(debitCard.getMainproductid()) > monto  ) {
													     return transactionService.registrarPago( debitCard,debitCard.getMainproductid(), monto );
														 }
				                     if ( transactionService.isRetiroPossible(debitcardId, monto ) ) {
				                  	   return transactionService.registrarPago( debitCard
				                  			 , transactionService.findProductRetiroDebitCard( debitCard, monto )
				                  			 , monto );
				                     }else {
				                  	   return "Pago no es posible realizar";
				                     } 
				                   }  );
	}
	
}
