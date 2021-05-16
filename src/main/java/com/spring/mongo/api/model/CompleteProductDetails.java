package com.spring.mongo.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompleteProductDetails {
	
	private int id;
	
	private PriceInfoWithDesc current_price;

	
	
	@Getter
	@Setter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public  static class PriceInfoWithDesc {
		
		private int value;
		private String currency_code;
		private String product_desc;
		
		
		
	}
}

