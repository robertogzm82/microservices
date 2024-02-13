package com.nttdata.product.api;

import java.util.ArrayList;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.product.model.Product;
import com.nttdata.product.model.ProductType;
import com.nttdata.product.service.CustomerService;
import com.nttdata.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class ProductController {
 
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/product")
	public Mono<?> register(@RequestBody Product product) {
		product.setTitulares(new ArrayList<String>());
		return isvalid(product)
		.flatMap( p -> { if(p) {
											log.info("terminó ejecución funcion isvalid");
											return productService.save(product) ;
										}	else
											return Mono.error(new RuntimeException("El producto no es válido"));
									 }  );
	}

	private Mono<Boolean> isvalid(Product product) {
		return customerService.findById(product.getCustomerId())
				.flatMap( customer  -> { if( customer.getTipo().name().equals("persona") )
					                        return isvalidCustomer(product);
				                         if( customer.getTipo().name().equals("empresa") )
				                          return isvalidEnterprise(product);
				                         else
				                        	return Mono.just(null);
				                       });
	}

	private Mono<Boolean> isvalidEnterprise(Product product) {
		return Mono.just(true);
	}

	private Mono<Boolean> isvalidCustomer(Product product) {
		return isvalidCantProdAhorro(product)
		//.flatMap( p -> { return Mono.just(p); } 
		// );
		.zipWith(isvalidCantProdCorrientePlazoFijo(product) , ( p, q ) ->	{ if (  p==true & q==true )
																																				return true;
																																		  else
																																		  	return false; 
																																			}	 )
		.doOnNext( p -> log.info(" validación suma cant prod_ahorro + cant prod_plazofijo = " + p));
	}

	private  Mono<Boolean> isvalidCantProdAhorro(Product product) {
		return productService.countByCustomerIdAndTipo(product.getCustomerId(), "ahorro" )
				.flatMap( p -> { if(p==0)  
					        			   return Mono.just(true); 
				                 else 
				                	 return Mono.just(false);
											 } )
				.doOnNext( p -> log.info(""+p) ) ;
	}

	private  Mono<Boolean> isvalidCantProdCorrientePlazoFijo(Product product) {
		Mono<Long> cant_prod_cuentacorriente = productService.countByCustomerIdAndTipo(product.getCustomerId(), "cuentaCorriente" ) ;
		Mono<Long> cant_prod_plazofijo = productService.countByCustomerIdAndTipo(product.getCustomerId(), "plazoFijo" ) ;
		return cant_prod_cuentacorriente
				.zipWith( cant_prod_plazofijo , (p, q) -> p + q  )
				.flatMap( p -> { if( p <= 2)  
					        			   return Mono.just(true); 
				                 else 
				                	 return Mono.just(false);
											 } ) ;
	}
	
	@GetMapping(value = "/product")
	public Flux<Product> getAll() {
	 	return productService.getAll();
	}
	
	@GetMapping(value = "/product/{id}")
	public Mono<Product> getbyId(@PathVariable(value="id") String productid) {
	 	return productService.findById(productid);
	}
	
	
	@GetMapping(value = "/product/customer/{id}")
	public Flux<Product> findByCustomerId(@PathVariable String id) {
		log.info("llamado al api product , metodo customer, tipo get de consulta- customerid=" + id);
	 	return productService.findByCustomerId(id);
	}
	
	@GetMapping(value = "/product/count/all")
	public Mono<Long> countByCustomerId(@RequestParam("customerid") String id) {
	 	return productService.countByCustomerId(id);
	}
	
	@GetMapping(value = "/product/count/filtered")
	public Mono<Long> countByCustomerIdAndTipo( @RequestParam("customerid") String customerid
			                                       ,@RequestParam("tipo") String tipo ) {
	 	return productService.countByCustomerIdAndTipo(customerid,tipo);
	}
	
	@GetMapping(value = "/product/transfer")
	public Mono<String> transfer( @RequestParam("productid_ini") String productid_ini
      ,@RequestParam("product_fin") String productid_fin
      ,@RequestParam("monto") Integer monto ) {
		return Mono.just("Transferencia Exitosa");
	}
}
