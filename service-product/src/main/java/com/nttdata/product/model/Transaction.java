package com.nttdata.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	private String id;
	
	private String customerId;
	
	private String productId;
	
	private TransactionType tipo;
	
	private Integer monto;
	
	private Date date;
	
	public Transaction(Product product, String tipo,Integer monto , Date fecha  ) {
		super();
		this.customerId = product.getCustomerId();
		this.productId = product.getId() ; 
		this.tipo = TransactionType.valueOf(tipo) ;
		this.monto = monto;
		this.date =  fecha;
	}

	private Date StringtoDate(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-DDThh:mmTZD"); 
		Date date = null;
		try {
			date = formato.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
