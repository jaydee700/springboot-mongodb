package com.spring.mongo.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "ProductDescription")
public class ProductDescription {
	@Id
	private int id;
	private String prod_desc;
	private String prod_name;
}