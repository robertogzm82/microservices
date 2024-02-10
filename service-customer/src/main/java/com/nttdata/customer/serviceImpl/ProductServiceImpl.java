package com.nttdata.customer.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.customer.model.Product;
import com.nttdata.customer.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	private WebClient webClient;
	
	public ProductServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }
	
	@Override
	public Mono<Boolean> existsById(String id) {
		return  webClient.get()
				.uri("/product/exist/"+id)
				.retrieve()
				.bodyToMono(Boolean.class);
	}
	
	@Override
	public Flux<Product> findByCustomerId(String customerid) {
		return  webClient.get()
				.uri("/product/customer/"+customerid)
				.retrieve()
				.bodyToFlux(Product.class);
	}

	@Override
	public Mono<Integer> countByCustomerIdAndTipo(String customerid, String tipo) {
		return  webClient
				.get()
				.uri("/product/count/filtered/?customerid="+customerid+"&tipo="+tipo)
				.retrieve()
				.bodyToMono(Integer.class);
	}

	@Override
	public Mono<Product> findById(String productid) {
		return  webClient
				.get()
				.uri("/product/"+productid)
				.retrieve()
				.bodyToMono(Product.class);
	}
	
}
