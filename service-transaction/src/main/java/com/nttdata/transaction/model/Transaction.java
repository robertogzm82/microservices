package com.nttdata.transaction.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

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
	
	private Date date;
	
	public Transaction(String customer_id, String product_id, String tipo_s, int monto) {
		super();
		this.customerId = customer_id;
		this.productId = product_id;
		this.tipo = TransactionType.valueOf(tipo_s);
		this.monto = monto;
		this.date = new Date();
	}
}
