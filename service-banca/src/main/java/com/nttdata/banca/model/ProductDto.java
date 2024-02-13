package com.nttdata.banca.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	String idproduct;
	String tipo;
	Integer Saldo;
	
	public static ProductDto ProductToProductDto(Product product, Integer saldo) {
		return new ProductDto( product.getId()
				                  ,product.getTipo().name()
				                  ,saldo );
		
	}
	
}
