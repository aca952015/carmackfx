package com.istudio.chatroom.server;

import com.istudio.carmackfx.interfaces.AuthProvider;
import com.istudio.chatroom.server.auth.ChatRoomAuthProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class ChatRoomApplication {

	@Bean
	public AuthProvider authProvider() {

		return new ChatRoomAuthProvider();
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ChatRoomApplication.class, args);
	}
}
