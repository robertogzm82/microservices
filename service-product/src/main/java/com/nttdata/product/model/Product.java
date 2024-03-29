package com.nttdata.product.model;
import java.io.Serializable;
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
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Nonnull
	private String customerId;
	 
	private ProductType tipo;
	
	private List<String> titulares;
	
	@Nonnull
	private int limite;
	
	private int monto_inicial;
	
	//@DBRef
	//private List<Transaction> transactions;
	
}
