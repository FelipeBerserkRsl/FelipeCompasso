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

	@Autowired
	ProductRepository productRepository;

	public ResponseEntity<?> postMappingValidation(Product product) {
		log.info("serviço de post");
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

			productRepository.save(product);

			return ResponseEntity.status(201).body(product);
		}

	}

	public List<Product> getList() {
		log.info("serviço get lista produtos");
		return productRepository.findAll();
	}

	public ResponseEntity<?> deleteProduct(String id) {
		log.info("serviço delete produtos");
		Optional<Product> findById = productRepository.findById(id);
		if (findById.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().body(null);
		} else {
			return ResponseEntity.status(404).body(null);
		}
	}

	public ResponseEntity<Optional<Product>> getProdutoById(String id) {

		log.info("serviço get produto por id");
		Optional<Product> findById = productRepository.findById(id);

		if (findById.isPresent()) {
			return ResponseEntity.ok().body(findById);
		} else {
			return ResponseEntity.status(404).body(null);
		}

	}

	public Product updateProduct(Product produto, String id) {
		log.info("serviço update produto");
		Optional<Product> findById = productRepository.findById(id);
		Product produto2 = findById.get();
		produto2.setDescription(produto.getDescription());
		produto2.setName(produto.getName());
		produto2.setPrice(produto.getPrice());
		produto2.setId(id);
		productRepository.save(produto2);
		return produto2;

	}

	public List<Product> getProdutoByFilter(String q, Double min, Double max) {

		log.info("serviço get produto by filter");

		if (min != null && max == null && q == null) {
			return productRepository.findByPriceSearchMin(min);
		}

		if (min != null && max != null && q == null) {
			return productRepository.findByPriceSearchMinAndMax(min, max);
		}

		if (min != null && max != null && q != null) {

			return productRepository.findByPriceSearchMaxMinAndQ(min, max, q);

		}

		if (min == null && max != null && q == null) {
			return productRepository.findByPriceSearchMax(max);
		}

		if (min == null && max != null && q != null) {
			return productRepository.findByPriceSearchMaxAndQ(max, q);
		}

		if (min != null && max == null && q != null) {
			return productRepository.findByPriceSearchMinAndQ(min, q);
		}

		if (min == null && max == null && q != null) {
			return productRepository.findByQ(q);
		}

		return null;

	}

}