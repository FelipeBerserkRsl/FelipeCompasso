package com.example.desafio.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.desafio.model.Erro;
import com.example.desafio.model.Product;
import com.example.desafio.repository.ProductRepository;

@Service
public class ProductService {
	
	Logger log = LoggerFactory.getLogger(ProductService.class);

	static Integer i = 0;
	@Autowired
	ProductRepository productRepository;

	public ResponseEntity<?> validation(Product product) {
		Erro er = new Erro();
		if (product.getName() == null) {

			er.setMessage("Name Can't be null");
			return ResponseEntity.status(400).body(er);
		} else if (product.getDescription() == null) {

			er.setMessage("Description can't be null");
			return ResponseEntity.status(400).body(er);
		} else if (product.getPrice() == null) {

			er.setMessage("Price can't be null");
			return ResponseEntity.status(400).body(er);
		} else if (product.getPrice() < 0) {
			er.setMessage("Price can't be negative");

			return ResponseEntity.status(400).body(er);
		} else {
			product.setId(i.toString());
			productRepository.save(product);
			i++;
			return ResponseEntity.status(201).body(product);
		}

	}

	public List<Product> getList() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}
	
	public void deleteProduct(String id) {
		productRepository.deleteById(id);
		
	}

	public Optional<Product> getProdutoById(String id) {
		Optional<Product> findById = productRepository.findById(id);
		return findById;
	}

	public Product updateProduct(Product produto, String id) {
		Optional<Product> findById = productRepository.findById(id);
		Product produto2 = findById.get();
		produto2.setDescription(produto.getDescription());
		produto2.setName(produto.getName());
		produto2.setPrice(produto.getPrice());
		produto2.setId(id);
		productRepository.save(produto2);
		return produto2;
		
	}

	public List<Product> getProdutoByFilter(String q, Long min, Long max) {
		
		
		return productRepository.findPrice(min, max, q);
		
	}

}
