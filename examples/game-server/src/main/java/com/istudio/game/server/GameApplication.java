package com.istudio.game.server;

import com.istudio.carmackfx.interfaces.AuthProvider;
import com.istudio.game.server.auth.GameAuthProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GameApplication {

	@Bean
	public AuthProvider authProvider() {

		return new GameAuthProvider();
	}

	public static void main(String[] args) {

		SpringApplication.run(GameApplication.class, args);
	}
}
