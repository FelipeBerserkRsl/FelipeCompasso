package com.example.desafio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.desafio.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	
	@Query("{$or: [ { 'price': { $gte: ?0 } }, {'price':{$lte: ?1} },{'name': {$eq: ?2 }}, {'description': { $eq: ?2 } } ] }") //mine
	public List<Product> findByPriceSearch(Double min_price, Double max_price, String q); 
	
	
	public List<Product> findByName(String name);

}
