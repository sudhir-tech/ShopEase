package com.sudhir.ecommercebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EcommercebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercebackendApplication.class, args);
	}

}
