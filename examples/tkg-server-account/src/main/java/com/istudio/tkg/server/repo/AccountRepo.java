package com.istudio.tkg.server.repo;

import com.istudio.tkg.server.model.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends MongoRepository<Account, String> {

    Account findByEmail(String email);
    boolean existsByEmail(String email);
}
