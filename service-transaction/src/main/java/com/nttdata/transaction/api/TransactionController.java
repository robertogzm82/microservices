package com.nttdata.transaction.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.transaction.model.Transaction;
import com.nttdata.transaction.service.ProductService;
import com.nttdata.transaction.service.TransactionService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class TransactionController {
 
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/transaction")
	public Mono<?> register(@RequestBody Transaction transaction) {
		log.info("customer_id:" + transaction.getCustomerId()+" , product_id=" + transaction.getProductId() );
		return isvalid(transaction)
		.flatMap( p -> { if(p) {
											 log.info("terminó ejecución funcion isvalid - transaction" );
											 return RulesAccountBanking(transaction);
										}	else
											 return Mono.just(new RuntimeException("El producto no es válido"));
									 }  );
	}
	
	private Mono<?> RulesAccountBanking(Transaction transaction) {
		return transactionService.iscuentaBancaria(transaction)
				.flatMap( p -> { if(p) {
										       return isproduc20mov(transaction);
				                 } else  {
				                   return transactionService.save(transaction); } 
										} ) ;
	}

	private Mono<Boolean> isvalid(Transaction transaction) {
		return productService.findById(transaction.getProductId())
				.flatMap( producto  -> { if( producto.getTipo().name().equals("tarjetaCredito") ||
																		 producto.getTipo().name().equals("tarjetaEmpresarial") )      
					                         return isvalidTransaction(transaction); 
				                         else
				                           return Mono.just(true);
				                       } ) ;
	}
	
	private Mono<?> isproduc20mov(Transaction transaction) {
		return  transactionService.findByProductId( transaction.getProductId() )
				.count()
				.flatMap(p -> { if ( p > 20 )
					                return  transactionService.save(transaction)
					            		 .flatMap( t -> { return transactionService.save_transaction_comision(transaction) ; 
					            		                } ) ;
							          else 
							      	    return transactionService.save(transaction); } );
	}
	
	private Mono<Boolean> isvalidTransaction(Transaction transaction) {
		return transactionService.getSaldoF( transaction.getProductId() )
				.zipWith( productService.findById(transaction.getProductId() ) )
				.map( tupla -> {if ( tupla.getT2().getLimite() < tupla.getT1() )
												  return true;
												else
											    return false;
				}  )  ;         
	}

	@GetMapping(value = "/transaction")
	public Flux<Transaction> getAll() {
	 	return transactionService.getAll();
	}
	
	@GetMapping(value = "/transaction/customer/{id}")
	public Flux<Transaction> getTransactionByCustomerid(@PathVariable(value="id") String customerid) {
	 	return transactionService.findByCustomerId(customerid);
	}
	
	@GetMapping(value = "/transaction/product/{id}")
	public Flux<Transaction> getTransactionByProductid(@PathVariable(value="id") String productid) {
	 	return transactionService.findByProductId(productid);
	}
	
	@GetMapping(value="/transaction/filtered")
	public Flux<Transaction> getTransactionFiltered( @RequestParam(value="productid") String productid
			                                           ,@RequestParam(value="dateini") String dateini
			                                           ,@RequestParam(value="datefin") String datefin ) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    LocalDate dateTimeini = LocalDate.parse(dateini, formatter) ;
    LocalDate dateTimefin = LocalDate.parse(datefin, formatter) ;
	 	return transactionService.findByDateBetween(dateTimeini, dateTimefin, productid) ;
	}
	
	@PostMapping(value = "/transaction/transfer")
	public Mono<Transaction> transfer( @RequestParam(value="productid_ini") String productid_ini
			                               , @RequestParam(value="productid_fin") String productid_fin
			                               , @RequestParam(value="monto") int monto )  {
		return  transactionService.transfer(productid_ini, productid_fin , monto );
	}
	
	@GetMapping(value = "/transaction/balance/{id}")
	public Mono<Integer> balance (@PathVariable("id") String productid ){
		return transactionService.getSaldoF(productid);
	}
}
