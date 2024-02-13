package com.nttdata.debitcard.api;

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

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class DebitCardController {
	
	@Autowired
	private DebitCardService debitCardService;
	
	@PostMapping(value="/debitCard/pago/{debitcardId}/{monto}")
	public Mono<String> pago (@PathVariable String monto, @PathVariable String debitcardId){
		return debitCardService.findByid(debitcardId)
				.map( debitCard -> { return new Transaction(debitCard,"pago", Integer.valueOf(monto) ); }  )
				.flatMap( t -> debitCardService.pago(t, debitcardId) );
	}
	
	@PostMapping(value="/debitCard/retiro/{debitcardId}/{monto}")
	public Mono<String> retiro (@PathVariable String monto, @PathVariable String debitcardId){
		return debitCardService.findByid(debitcardId)
				.map( debitCatd -> debitCardService.getProductsId(debitCatd, monto)   );
	}
	
}
