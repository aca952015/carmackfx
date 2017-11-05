package com.istudio.carmackfx.auth;

import com.istudio.carmackfx.auth.impls.AuthServiceImpl;
import com.istudio.carmackfx.interfaces.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ACA on 2017-6-7.
 */
@Configuration
public class AuthAutoConfiguration {

    @Bean
    public AuthService authService() {

        return new AuthServiceImpl();
    }
}