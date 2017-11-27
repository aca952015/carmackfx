package com.istudio.tkg.server.service;

import com.istudio.carmackfx.domain.AuthResult;
import com.istudio.carmackfx.domain.User;
import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.tkg.server.model.domain.Account;
import com.istudio.tkg.server.model.dto.LoginRequest;
import com.istudio.tkg.server.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService implements SecurityService<LoginRequest> {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public AuthResult auth(LoginRequest request) {

        AuthResult result = new AuthResult();

        Account account = accountRepo.find(request.getUsername());
        if(account != null && account.getPassword().equals(request.getPassword())) {

            User user = new User();
            user.setUsername(account.getEmail());
            user.setNickname(account.getNickname());

            result.setSuccess(true);
            result.setUser(user);
        }

        return result;
    }
}
