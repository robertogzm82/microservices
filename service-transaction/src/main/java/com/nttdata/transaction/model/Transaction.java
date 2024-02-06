package com.nttdata.transaction.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="transaction")
public class Transaction {

	@Id
	private String id;
	
	private String customerId;
	
	private String productId;
	
	private TransactionType tipo;
	
	private Integer monto;
	
	private LocalDateTime date;
	
}
