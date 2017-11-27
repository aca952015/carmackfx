package com.istudio.tkg.server.repo;

import com.istudio.tkg.server.model.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountRepo {

    int exists(String email);
    int create(Account account);
    Account find(String email);
}
