package com.nttdata.microservices.model;

import java.util.List;

import com.nttdata.microservices.model.ProductType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class Product {

	private String id;

	private String customerId;
	
	private ProductType tipo;
	
	private List<String> titulares;
	 
	private int limite;

}
