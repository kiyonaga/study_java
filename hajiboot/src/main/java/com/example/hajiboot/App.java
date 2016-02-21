package com.example.hajiboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@RestController
@EnableAutoConfiguration
public class App
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	@RequestMapping("/")
	String home()
	{
		logger.info("HOHOHO World!");
		return "HOHOHO world!";
	}

	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
