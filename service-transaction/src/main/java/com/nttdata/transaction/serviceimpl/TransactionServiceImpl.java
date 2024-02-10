package com.nttdata.transaction.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.transaction.model.Transaction;
import com.nttdata.transaction.model.TransactionType;
import com.nttdata.transaction.repository.TransactionRepository;
import com.nttdata.transaction.service.ProductService;
import com.nttdata.transaction.service.TransactionService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Flux<Transaction> getAll() {
		return transactionRepository.findAll();
	}

	@Override
	public Mono<Transaction> save(Transaction transaction) {
		log.info("Se grabó la transaccion." + transaction.getId());
		return  transactionRepository.save(transaction);
	}

	@Override
	public Mono<Integer> getSaldoF (String productid) {
		return transactionRepository.findByProductId(productid)
				.flatMap( t -> { if (t.getTipo().name().equals("pago") || t.getTipo().name().equals("deposito") ) 
						               return Flux.just( t.getMonto() );
						             else if (t.getTipo().name().equals("consumo") || t.getTipo().name().equals("comision")
						            		 || t.getTipo().name().equals("retiro") )
						            	 return Flux.just( t.getMonto()*(-1) ) ;
						             else	 
						               return Flux.just( 0 );
						})
				.reduce( 0 , Integer::sum );
		//	  .concatWith( )
		//return saldo;
	}
	
	@Override
	public Flux<Transaction> findByProductId(String productid) {
		return transactionRepository.findByProductId(productid);
	}

	@Override
	public Flux<Transaction> findByCustomerId(String customerid) {
		return transactionRepository.findByCustomerId(customerid);
	}

	public Mono<Boolean> iscuentaBancaria(Transaction transaction){
		String[] array = {"ahorro" , "cuentaCorriente" , "plazoFijo" };
		List<String> cuentasBancarias = Arrays.asList(array);
		return productService.getTipoProductoTransaction(transaction)
				.map( p -> cuentasBancarias.contains(p) );
	}
	
	public Mono<Transaction> save_transaction_comision(Transaction transaction) {
		Double monto_recargo = transaction.getMonto()*0.10  ;
		transaction.setMonto( monto_recargo.intValue() );
		transaction.setTipo(TransactionType.comision);
		log.info("Se guardó transacción con comisión");
		return save(transaction);
	}

	@Override
	public Flux<Transaction> findByDateBetween (LocalDate start, LocalDate end,String productid) {
		return transactionRepository.findByDateBetween(start, end, productid);
	}

	@Override
	public Mono<Transaction> transfer(String productid_ini, String productid_fin, int monto) {
		return retiro(productid_ini  , monto)
				.then( deposito(productid_fin, monto) );
	}
	 
	private  Mono<Transaction> retiro (String productid_ini, int monto){
		return productService.findById(productid_ini)
				.flatMap( p ->  { return save(new Transaction( p.getCustomerId(), productid_ini,
						                          "retiro",monto) ) ;
				                }
								) ;
	}
	
	private  Mono<Transaction> deposito (String productid_ini, int monto){
		return productService.findById(productid_ini)
				.flatMap( p ->  { return save( new Transaction( p.getCustomerId(), productid_ini,
                                       "deposito", monto) ) ;
												}
								) ;
	}
	
}
