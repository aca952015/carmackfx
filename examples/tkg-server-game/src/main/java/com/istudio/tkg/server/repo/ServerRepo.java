package com.istudio.tkg.server.repo;

import com.istudio.tkg.server.model.domain.Server;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ServerRepo {

    Server[] findAll();
}
