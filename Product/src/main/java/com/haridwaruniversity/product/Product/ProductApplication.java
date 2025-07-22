package com.haridwaruniversity.product.Product;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// Or if you need to specify:
// @SpringBootApplication(scanBasePackages = "com.haridwaruniversity.product")
public class ProductApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
