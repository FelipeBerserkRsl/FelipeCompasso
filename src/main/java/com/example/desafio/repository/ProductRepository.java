package com.example.desafio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.desafio.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	public <List>Product findByName(String nome);
	public <List>Product findByDescription(String description);
	public <List>Product findByPrice(Long price);
	

}
