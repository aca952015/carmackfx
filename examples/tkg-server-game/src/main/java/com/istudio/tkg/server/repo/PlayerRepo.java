package com.istudio.tkg.server.repo;

import com.istudio.tkg.server.model.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends MongoRepository<Player, String> {

    Player findByOwner(String owner);
    boolean existsByOwner(String owner);
}
