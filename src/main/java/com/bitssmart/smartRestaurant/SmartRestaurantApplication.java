package com.bitssmart.smartRestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.bitssmart.smartRestaurant.Repository")
@EntityScan(basePackages = {"com.bitssmart.smartRestaurant.Model"})
@ComponentScan(basePackages = {"com.bitssmart.smartRestaurant"})
public class SmartRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartRestaurantApplication.class, args);
	}

}
