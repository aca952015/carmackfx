package com.istudio.chatroom.agent;

import com.istudio.carmackfx.agent.AgentServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatRoomAgentApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ChatRoomAgentApplication.class, args);

		AgentServer server = new AgentServer(18000, 1);
		server.noDelay(1, 10, 2, 1);
		server.setMinRto(10);
		server.wndSize(64, 64);
		server.setTimeout(10 * 1000);
		server.setMtu(512);
		server.start();
	}
}
