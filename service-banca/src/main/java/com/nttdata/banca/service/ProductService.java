package com.nttdata.banca.service;

import com.nttdata.banca.model.Product;

import reactor.core.publisher.Flux;

public interface ProductService {

	  Flux<Product> findByCustomerid(String customerid);

}
