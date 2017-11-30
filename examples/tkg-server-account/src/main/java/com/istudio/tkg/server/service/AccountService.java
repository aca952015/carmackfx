package com.istudio.tkg.server.service;

import com.istudio.carmackfx.annotation.*;
import com.istudio.carmackfx.protocol.MessageContext;
import com.istudio.carmackfx.protocol.MessageException;
import com.istudio.tkg.server.model.domain.Account;
import com.istudio.tkg.server.repo.AccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("IAccountService")
@TPublic
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @TMethod("Register")
    public boolean register(@TContext MessageContext context,
                            @TParam("email") String email,
                            @TParam("password") String password) throws MessageException {

        log.info("user register: {}, {}", email, password);

        if(accountRepo.existsByEmail(email)) {
            throw new MessageException("Email已经被使用。");
        }

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);

        if(accountRepo.insert(account) == null) {
            return false;
        }

        return true;
    }
}
