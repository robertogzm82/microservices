package com.nttdata.debitcard.model;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="product")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	private String id;

	@Nonnull
	private String customerId;
	 
	private ProductType tipo;
	
	private List<String> titulares;
	
	@Nonnull
	private int limite;
	
	//@DBRef
	//private List<Transaction> transactions;
	
}
