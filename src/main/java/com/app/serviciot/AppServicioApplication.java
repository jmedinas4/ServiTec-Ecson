package com.app.serviciot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AppServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppServicioApplication.class, args);
	}

}
