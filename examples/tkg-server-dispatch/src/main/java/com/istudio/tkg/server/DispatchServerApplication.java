package com.istudio.tkg.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CommonsConfig.class)
public class DispatchServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispatchServerApplication.class, args);
	}
}
