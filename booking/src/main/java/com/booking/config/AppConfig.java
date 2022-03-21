package com.booking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class AppConfig {

	// class for defining configurations
	
	@Value("${fileName}")
	private String fileName;
	
	@Bean
	public String fileName() {
		return fileName;
	}
}
