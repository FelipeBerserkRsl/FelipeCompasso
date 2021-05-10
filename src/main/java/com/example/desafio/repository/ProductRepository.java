package com.example.desafio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.desafio.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	//{$and: [ {}, {}, {$or:[{},{}]} ] }
	//json recebe um array de opcoes

	@Query("{ 'price' : { $gte: ?0 } }") // mine
	public List<Product> findByPriceSearchMin(Double min_price);

	@Query("{ $and: [ { 'price': { $gte: ?0 } }, {'price':{$lte: ?1} } ] }")
	public List<Product> findByPriceSearchMinAndMax(Double min_price, Double max_price);

	@Query("{ 'price' : { $lte: ?0 } }") // mine
	public List<Product> findByPriceSearchMax(Double max_price);
	      //{$and: [ {'price': { $lte: ?0 }}, {'price':{$lte: ?1}}, {$or:[{                  }, {                          } ] } ]}
	@Query("{$and: [ {'price': { $gte: ?0 } }, {'price':{$lte: ?1}}, {$or:[{'name': {$eq: ?2 }}, {'description': { $eq: ?2 }} ] } ]}") //mine
	public List<Product> findByPriceSearchMaxMinAndQ(Double min_price, Double max_price, String q); 
	
	@Query("{$and: [{'price':{$lte: ?0}}, {$or:[{'name': {$eq: ?1 }}, {'description': { $eq: ?1 }} ] } ]}")
	public List<Product> findByPriceSearchMaxAndQ(Double max_price, String q);
	
	@Query("{$and: [{'price':{$gte: ?0}}, {$or:[{'name': {$eq: ?1 }}, {'description': { $eq: ?1 }} ] } ]}")
	public List<Product> findByPriceSearchMinAndQ(Double min_price, String q);

	@Query("{$or:[ {'name': {$eq: ?0 }}, {'description': { $eq: ?0 }} ] }")
	public List<Product> findByQ(String q);

}
