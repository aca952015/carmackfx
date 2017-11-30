package com.istudio.tkg.server.service;

import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.tkg.server.model.domain.Account;
import com.istudio.tkg.server.model.request.LoginRequest;
import com.istudio.tkg.server.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService implements SecurityService<LoginRequest> {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public User auth(LoginRequest request) {

        Account account = accountRepo.findByEmail(request.getUsername());
        if(account != null && account.getPassword().equals(request.getPassword())) {

            User user = new User();
            user.setId(account.getId());
            user.setUsername(account.getEmail());
            user.setNickname(account.getNickname());

            return user;
        }

        return null;
    }
}
