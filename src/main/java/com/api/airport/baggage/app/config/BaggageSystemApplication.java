package com.api.airport.baggage.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Narasimha Kishore Kaipa
 *
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.api.airport.baggage.app.config")
public class BaggageSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BaggageSystemApplication.class, args);
	} 
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BaggageSystemApplication.class);
	}
}
