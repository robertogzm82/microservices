package com.nttdata.banca.model;

import java.util.List;
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
	
	//@DBRef
	//private List<Transaction> transactions;
	
}
