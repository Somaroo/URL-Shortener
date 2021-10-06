package com.URL.Shortener.URLShortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"Controller"})
//@ComponentScan(basePackages = {"Service"})
public class UrlShortenerApplication {

	public static void main(String[] args) {

		SpringApplication.run(UrlShortenerApplication.class, args);
	}

}
