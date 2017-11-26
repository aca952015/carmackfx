package com.istudio.tkg.server.account.service;

import com.istudio.carmackfx.annotation.TContext;
import com.istudio.carmackfx.annotation.TMethod;
import com.istudio.carmackfx.annotation.TParam;
import com.istudio.carmackfx.protocol.MessageContext;
import com.istudio.carmackfx.protocol.MessageException;
import com.istudio.tkg.server.account.model.Account;
import com.istudio.tkg.server.account.repo.AccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("IAccountService")
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @TMethod("Register")
    public boolean register(@TContext MessageContext context,
                            @TParam("email") String email,
                            @TParam("password") String password) throws MessageException {

        log.info("user register: {}, {}", email, password);

        if(accountRepo.exists(email) > 0) {
            throw new MessageException("Email已经被使用。");
        }

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);

        if(accountRepo.create(account) < 1) {
            return false;
        }

        return true;
    }
}
