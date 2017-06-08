package com.istudio.chatroom.server;

import com.istudio.carmackfx.interfaces.AuthProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatRoomApplication {

	@Bean
	public AuthProvider authProvider() {

		return new ChatRoomAuthProvider();
	}

	public static void main(String[] args) {

		SpringApplication.run(ChatRoomApplication.class, args);
	}
}
