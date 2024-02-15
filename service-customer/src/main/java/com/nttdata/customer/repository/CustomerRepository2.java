package com.nttdata.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.customer.model.Customer;

@Repository
public interface CustomerRepository2 extends MongoRepository <Customer, String>{


}
