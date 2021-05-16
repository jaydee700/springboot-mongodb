package com.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.mongo.api.model.ProductDescription;

public interface ProductDescriptionRepository extends MongoRepository<ProductDescription, Integer> {

}
