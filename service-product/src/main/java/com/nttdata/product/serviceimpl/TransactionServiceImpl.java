package com.nttdata.product.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.product.api.ProductController;
import com.nttdata.product.model.Customer;
import com.nttdata.product.model.Product;
import com.nttdata.product.model.ProductDto;
import com.nttdata.product.model.Transaction;
import com.nttdata.product.service.CustomerService;
import com.nttdata.product.service.ProductService;
import com.nttdata.product.service.TransactionService;

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

	 
}
