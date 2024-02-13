package com.nttdata.debitcard.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="debitcardtransaction")
public class DebitCardTransaction {

	@Id
	private String id;
	
	private String DebitCardId;
	
	private String TransactionId;

	public DebitCardTransaction(String DebitCardId , String TransactionId ) {
		this.DebitCardId = DebitCardId ;
		this.TransactionId = TransactionId;
	}
}
