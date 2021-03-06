package com.istudio.tkg.server.repo;

import com.istudio.tkg.server.model.domain.Server;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepo extends MongoRepository<Server, String> {

}
