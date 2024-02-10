package com.nttdata.transaction.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.transaction.model.Product;
import com.nttdata.transaction.model.Transaction;
import com.nttdata.transaction.service.ProductService;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	private WebClient webClient;
	
	public ProductServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }
	
	@Override
	public Mono<Product> findById(String id) {
		return webClient
				.get()
				.uri("/product/"+id)
				.retrieve()
				.bodyToMono(Product.class);
	}

	@Override
	public Mono<String> getTipoProductoTransaction(Transaction transaction) {
		return findById(transaction.getProductId())
				.map( t -> { return t.getTipo().name(); } );
	}

}
