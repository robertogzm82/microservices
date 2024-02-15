package com.nttdata.customer.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document(collection="customer")
@Setter
@Getter
@AllArgsConstructor
@JsonSerialize(using = CustomerSerializer.class)
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private CustomerType tipo;
	
	private String name;
	
}
