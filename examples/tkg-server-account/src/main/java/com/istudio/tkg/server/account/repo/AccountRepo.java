package com.istudio.tkg.server.account.repo;

import com.istudio.tkg.server.account.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountRepo {

    int exists(String email);
    int create(Account account);
}
