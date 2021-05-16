package com.spring.mongo.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Product")
public class Product {
	@Id
	private int id;
	
	private PriceInfo current_price;
	
	@Getter
	@Setter
	@ToString
	public class PriceInfo {
		
		private int value;
		private String currency_code;
	}
	
}
