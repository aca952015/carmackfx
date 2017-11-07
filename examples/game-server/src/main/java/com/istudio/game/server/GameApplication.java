package com.istudio.game.server;

import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.game.server.security.GameSecurityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GameApplication {

	@Bean
	public SecurityService securityService() {

		return new GameSecurityService();
	}

	public static void main(String[] args) {

		SpringApplication.run(GameApplication.class, args);
	}
}
