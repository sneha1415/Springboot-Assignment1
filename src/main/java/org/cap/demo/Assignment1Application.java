package org.cap.demo;

import org.cap.demo.controller.StudentController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Assignment1Application {
	
	static Logger logger = LoggerFactory.getLogger(Assignment1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Application.class, args);
		logger.info("Application is started ");
	}
	
	@RequestMapping("/hello")
	public String sayHello()
	{
		return "Good Morning ! Welcome";
	}

		
	

}
