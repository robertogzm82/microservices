package com.nttdata.customer.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.customer.model.Customer;
import com.nttdata.customer.repository.CustomerRepository;
import com.nttdata.customer.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Flux<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Mono<Customer> save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Mono<Customer> findById(String id) {
		return customerRepository.findById(id);
	}

	@Override
	public Mono<Boolean> existsById(String id) {
		return customerRepository.existsById(id);
	}
	
}
