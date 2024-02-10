package com.nttdata.customer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.customer.model.Customer;
import com.nttdata.customer.service.CustomerService;
import com.nttdata.customer.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@Slf4j
public class CustomerController {

	  @Autowired
	  private CustomerService customerService;
	  
	  @PostMapping(value="/customer")
	  public Mono<Customer> register(@RequestBody Customer customer) {
	    return customerService.save(customer);
	  }
	
	  @GetMapping(value="/customer")
	  public Flux<Customer> getAll() {
	    return customerService.getAll() ;
	  }
	  
	  @GetMapping(value="/customer/{id}")
	  public Mono<Customer> findById(@PathVariable String id) {
	    return customerService.findById(id) ;
	  }
	  
	  @GetMapping(value="/customer/exist/{id}")
	  public Mono<Boolean> exitsById(@PathVariable String id) {
	    return customerService.existsById(id) ;
	  }
	 
	  @GetMapping(value="/customer/isVIP/{id}")
	  public Mono<Boolean> isCustomerVIP (@PathVariable String id){
	  	return customerService.isCustomerVIP(id);
	  }
	  
	  @GetMapping(value="/customer/isPYME/{id}")
	  public Mono<Boolean> isCustomerPYME (@PathVariable String id){
	  	return customerService.isCustomerPYME(id);
	  }
	  
}
