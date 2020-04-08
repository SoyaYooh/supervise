package com.linkcheers.supervise;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.linkcheers.supervise.*"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SuperviseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperviseApplication.class, args);
	}

}
