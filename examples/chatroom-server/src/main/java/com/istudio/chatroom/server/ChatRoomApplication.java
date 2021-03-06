package com.istudio.chatroom.server;

import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.chatroom.server.security.ChatRoomSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@Slf4j
public class ChatRoomApplication {

	public static void main(String[] args) {

		SpringApplication.run(ChatRoomApplication.class, args);
	}
}
