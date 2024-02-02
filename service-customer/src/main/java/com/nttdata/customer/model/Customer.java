package com.nttdata.customer.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection="customer")
@Setter
@Getter
@AllArgsConstructor
public class Customer {

	@Id
	private String id;
	
	private CustomerType tipo;
	
	private String name;
	
	private List<String> productosId;
	 
}
