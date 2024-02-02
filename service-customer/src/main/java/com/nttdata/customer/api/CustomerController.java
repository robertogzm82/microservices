package com.nttdata.customer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.customer.model.Customer;
import com.nttdata.customer.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
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
}
