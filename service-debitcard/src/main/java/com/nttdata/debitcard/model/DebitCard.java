package com.nttdata.debitcard.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="debitcard")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DebitCard {

	@Id
	private String id;

	private String number;
	
	private String customerid;
	
	private String mainproductid;
	
	private List<String> auxproductid;
	
}
