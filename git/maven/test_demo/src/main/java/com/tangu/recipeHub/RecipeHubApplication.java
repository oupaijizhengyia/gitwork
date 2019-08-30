package com.tangu.recipeHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="com.tangu")
public class RecipeHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeHubApplication.class, args);
	}
}
