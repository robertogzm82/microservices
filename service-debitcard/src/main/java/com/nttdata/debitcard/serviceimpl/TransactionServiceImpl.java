package com.nttdata.debitcard.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.debitcard.model.Product;
import com.nttdata.debitcard.model.Transaction;
import com.nttdata.debitcard.service.TransactionService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
	
	private WebClient webClient;
	
	public TransactionServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
        		.baseUrl("http://localhost:8084")
        		.filter( (request, next) -> {
              System.out.println("Enviando solicitud al microservicio...");
              System.out.println("Cuerpo de la solicitud: " + request.body());
              return next.exchange(request);
              } )
        		.build()
        		;
    }
	
	@Override
	public Mono<Transaction> saveTransacionProduct(Product product, Integer monto, Date fecha) {
		log.info("método consulta microservicio transaction");
		return webClient
				.post()
				.uri("/transaction")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue( new Transaction(product , "pago", monto , fecha)  )
				//.httpRequest( request.body() )
				//.doOnRequest( (request ) -> System.out.println("Cuerpo de la solicitud: " + request.body()) )
				.retrieve()
				.bodyToMono(Transaction.class);
	}
	
	@Override
	public Mono<Integer> getSaldo(String productid) {
		return  webClient.get()
				.uri("/transaction/balance/"+productid)
				.retrieve()
				.bodyToMono(Integer.class);
	}

	@Override
	public Mono<Transaction> save(Transaction transaction) {
		return webClient
				.post()
				.uri("/transaction")
				.body( BodyInserters.fromValue(transaction) )
				.retrieve()
				.bodyToMono( Transaction.class );
	}
	 
}
