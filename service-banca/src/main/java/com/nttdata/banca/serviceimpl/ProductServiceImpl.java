package com.nttdata.banca.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.banca.model.Product;
import com.nttdata.banca.service.ProductService;

import reactor.core.publisher.Flux;

@Service
public class ProductServiceImpl implements ProductService {

	private WebClient webClient;
	
	public ProductServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

	@Override
	public Flux<Product> findByCustomerid(String customerid) {
		return webClient
				.get()
				.uri("/product/customer/" + customerid)  
				.retrieve()
				.bodyToFlux(Product.class);
	}

}
