package com.nttdata.debitcard.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.debitcard.model.DebitCard;
import com.nttdata.debitcard.model.DebitCardTransaction;
import com.nttdata.debitcard.model.Transaction;
import com.nttdata.debitcard.repository.DebitCardRepository;
import com.nttdata.debitcard.repository.DebitCardTransactionRepository;
import com.nttdata.debitcard.service.DebitCardService;
import com.nttdata.debitcard.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitCardServiceImpl implements DebitCardService {
  
	@Autowired
	private TransactionService trasactionService;
	
	@Autowired
	private DebitCardRepository debitCardRepository;
	
	@Autowired
	private DebitCardTransactionRepository debitCardTransactionRepository;
	
	@Override
	public Mono<DebitCard> save(DebitCard debitCard) {
		return debitCardRepository.save(debitCard);
	}

	@Override
	public Mono<DebitCard> retiro(DebitCard debitCard) {
		return null;
	}

	@Override
	public Mono<String> pago(Transaction transaction, String DebitCardId) {
		return trasactionService.save(transaction)
				.flatMap( t -> debitCardTransactionRepository.save( new DebitCardTransaction ( DebitCardId  ,t.getId()  )   )
				.map( d -> "Pago realizado") );
	}

	@Override
	public Mono<DebitCard> findByid(String id) {
		return debitCardRepository.findById(id);
	}

	@Override
	public Mono<String> getProductsId(DebitCard debitCard, Integer monto) {
		return Mono.just(debitCard.getMainproductid())
				.flatMap( productid -> hasEnoughBalance(productid,monto ) )
				.map(  b  -> { if(b) {return "Pago realizado"; }
											else { return null; } } );
	}

	private Mono<Integer> hasEnoughBalance(String productid, Integer monto) {
		return trasactionService.getSaldo(productid)
				.map( i -> { if (i>=monto) return true;
									else return false; } );
	}

}
