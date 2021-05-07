package com.example.desafio.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio.model.Product;
import com.example.desafio.service.ProductService;




@RestController
public class DesafioController {
	
	Logger logger = LoggerFactory.getLogger(DesafioController.class);

	@Autowired
	ProductService productService;

	@PostMapping("/products")
	public ResponseEntity<?> postProduct(@RequestBody Product product) {
		
		ResponseEntity<?> validation = productService.validation(product);

		return validation;
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<?> putProduto(@RequestBody Product product, @PathVariable String id) {
		Product updateProduct = productService.updateProduct(product, id);
		return ResponseEntity.ok().body(updateProduct);
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProduct() {
		return ResponseEntity.ok().body(productService.getList());
	}

	@GetMapping("/products/{id}")
	public Optional<Product> getProductById(@PathVariable String id) {
		return productService.getProdutoById(id);
	}
	
	@GetMapping("/products/search")
	public Optional<Product> getProductByFilter(@PathVariable String q, Long min_price, Long max_price) {
		return productService.getProdutoByFilter(q, min_price, max_price);
	}



	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok().body(null);
	}

}
