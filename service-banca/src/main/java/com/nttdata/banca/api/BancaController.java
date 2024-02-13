package com.nttdata.banca.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Zip;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.banca.model.Customer;
import com.nttdata.banca.model.Product;
import com.nttdata.banca.model.ProductDto;
import com.nttdata.banca.service.ProductService;
import com.nttdata.banca.service.TransactionService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class BancaController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TransactionService transactionService;
	
  @GetMapping(value="/banca/report/{id}")
  public Flux<ProductDto> ReporteCustomersProduct(@PathVariable String id) {
  	log.info("Banca - Reporte");
    return productService.findByCustomerid(id)
    		//.doOnNext( System.out::println )
    		.map( product -> ProductDto.ProductToProductDto(product,0 ))
    		.flatMap( productdto -> { productdto.setSaldo(1);
    		                          return productaddSaldo(productdto) ;}  );
  }

	private Flux<Integer> getSaldo(String productid) {
		return transactionService.getFluxSaldoF(productid)
				.flatMap( t -> { return Flux.just(t); } );
	}
	
	private Flux<ProductDto> productaddSaldo(ProductDto pdto) {
		log.info("productid = " +pdto.getIdproduct());
		return  getSaldo(pdto.getIdproduct())
				.flatMap( i ->  { pdto.setSaldo(i);
					                return Flux.just(pdto); 
			 	                   } );
	}
	
	
	
}
