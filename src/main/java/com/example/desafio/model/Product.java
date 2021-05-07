package com.example.desafio.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.sun.istack.NotNull;

@Document(collection = "compasso")
public class Product {

	@Id
	private String id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private Double price; // valor deve ser positivo

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
