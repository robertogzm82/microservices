package com.nttdata.customer.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nttdata.customer.model.Customer;
import com.nttdata.customer.repository.CustomerRepository;
import com.nttdata.customer.repository.CustomerRepository2;
import com.nttdata.customer.service.CustomerService;
import com.nttdata.customer.service.ProductService;
import com.nttdata.customer.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerRepository2 customerRepository2;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Override
	public Flux<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Mono<Customer> save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	//@Cacheable(value = "customerCache" , key = "#id")
	public Mono<Customer> findById(String id) {
		return customerRepository.findById(id);
	}
	
	@Cacheable(value = "customerCache" , key = "#id")
	public Customer findByIdCacheable(String id) {
		//customerRepository2.findById(id);
		return customerRepository2.findById(id).get();
	}

	
	@Override
	public Mono<Boolean> existsById(String id) {
		return customerRepository.existsById(id);
	}

	@Override
	public Mono<Boolean> isCustomerVIP(String customerid) {
		return isCustomerhaveAPlazoFijo(customerid)
				.flatMap(p -> { if (p) 
					            return isCustomerhasAmount500(customerid); 
										else
				              return Mono.just(false);  }
					  );
	}

	public Mono<Boolean> isCustomerhaveAPlazoFijo(String customerid){
		return productService.findByCustomerId(customerid)
				.any( product -> product.getTipo().name().equals("plazoFijo" ) ) ;
	}
	
	public Mono<Boolean> isCustomerhasAmount500(String customerid){
		return productService.findByCustomerId(customerid)
				.flatMap( p ->  transactionService.getSaldo(p.getId()) )
				.any( t ->  t > 500  );
	}
	
	public Mono<Boolean> isCustomerhasCuentaCorriente(String customerid){
		return productService.findByCustomerId(customerid)
				.any( product -> product.getTipo().name().equals("cuentaCorriente" ) ) ;
	}
	
	@Override
	public Mono<Boolean> isCustomerPYME(String customerid) {
		return isCustomerhasCuentaCorriente(customerid);
	}


	
	
}
