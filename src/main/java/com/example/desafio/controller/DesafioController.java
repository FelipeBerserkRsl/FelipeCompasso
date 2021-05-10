package com.example.desafio.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
		logger.info("iniciando post produto");
		return productService.postMappingValidation(product);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<?> putProduto(@RequestBody Product product, @PathVariable String id) {
		logger.info("iniciando update produto");
		return ResponseEntity.ok().body(productService.updateProduct(product, id));
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProduct() {
		logger.info("iniciando get lista produtos");
		return ResponseEntity.ok().body(productService.getList());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable String id) {
		logger.info("iniciando get produtos por id");
		return productService.getProdutoById(id);
	}
	
	@GetMapping("/products/search")
	public List<Product> getProductByFilter(String q, Double min_price,  Double max_price) {
		logger.info("iniciando busca por filtros");
		return productService.getProdutoByFilter(q, min_price, max_price);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable String id) {
		logger.info("iniciando delete produto");
		return productService.deleteProduct(id);
		
	}

}
