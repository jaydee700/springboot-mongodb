package com.spring.mongo.api.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mongo.api.model.CompleteProductDetails;
import com.spring.mongo.api.model.Product;
import com.spring.mongo.api.model.ProductDescription;
import com.spring.mongo.api.repository.ProductDescriptionRepository;
import com.spring.mongo.api.repository.ProductRepository;

import ch.qos.logback.classic.Logger;




@RestController
public class ProductController {

	Logger logger = (Logger) LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductDescriptionRepository productDescriptionRepository;

	@PostMapping("/product")
	public String saveProduct(@RequestBody Product product) {
		System.out.println("Inside saveProduct ==" + product);
		repository.save(product);
		return "Product added with ID: " + product.getId();
	}

	@PostMapping("/productdescription")
	public String saveProductDescription(@RequestBody ProductDescription productDesc) {
		productDescriptionRepository.save(productDesc);
		return "productDesc added with ID: " + productDesc.getId();
	}
	
	@GetMapping("/findAllProducts")
	public List<Product> getProducts() {
		return repository.findAll();
	}

	@GetMapping("/findAllProducts/{id}")
	public Optional<Product> getProduct(@PathVariable int id) {
		return repository.findById(id);
}
	
	@GetMapping("/product/details/{id}")
	public ResponseEntity<CompleteProductDetails> getCompletProductDet(@PathVariable int id) {
		logger.debug("get complete product details");
		
		CompleteProductDetails completeProd = new CompleteProductDetails();

		CompleteProductDetails.PriceInfoWithDesc completeProdPriceWithDesc = new CompleteProductDetails.PriceInfoWithDesc();

		Optional<Product> product = repository.findById(id);
		
		Optional<ProductDescription>  productDes = productDescriptionRepository.findById(id);
		
		
		if(product.isPresent()) {
			
			completeProd.setId(product.get().getId());
			
			logger.error("Product ==" + product.get());
			logger.debug("Product Desc==" + productDes.get());
			
			Product.PriceInfo priceInfo =  product.get().getCurrent_price();
			logger.debug("priceInfo ==" + priceInfo);
			completeProdPriceWithDesc.setCurrency_code(priceInfo.getCurrency_code());
			completeProdPriceWithDesc.setValue(priceInfo.getValue());
			completeProdPriceWithDesc.setProduct_desc(productDes.get().getProd_desc());
			
			completeProd.setCurrent_price(completeProdPriceWithDesc);
		}
		
		return new ResponseEntity<CompleteProductDetails>(completeProd, HttpStatus.OK);
		
		
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		repository.deleteById(id);
		return "Product deleted with ID: " + id;
	}

}