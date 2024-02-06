package com.nttdata.product.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.product.repository.ProductRepository;
import com.nttdata.product.service.CustomerService;
import com.nttdata.product.service.ProductService;
import com.nttdata.product.model.Product;
import com.nttdata.product.model.ProductType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerService customerService ;

	@Override
	public Flux<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Mono<Product> save(Product product) {
		return  productRepository.save(product);
	}

	@Override
	public Mono<Product> findById(String id) {
		return productRepository.findById(id);
	}

	@Override
	public Mono<Boolean> existsById(String id) {
		return productRepository.existsById(id);
	}

	@Override
	public Flux<Product> findByCustomerId(String id) {
		return productRepository.findByCustomerId(id);
	}

	@Override
	public Mono<Long> countByCustomerId(String customerId) {
		return productRepository.countByCustomerId(customerId);
	}
  
	@Override
	public Mono<Long> countByCustomerIdAndTipo(String customerId, String tipo) {
		return productRepository.countByCustomerIdAndTipo(customerId, ProductType.valueOf(tipo));
	}
	
	
}
