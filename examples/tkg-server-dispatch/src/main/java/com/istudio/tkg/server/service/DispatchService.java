package com.istudio.tkg.server.service;

import com.istudio.carmackfx.annotation.TMethod;
import com.istudio.carmackfx.annotation.TPublic;
import com.istudio.carmackfx.exceptions.MessageException;
import com.istudio.tkg.server.repo.ServerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("IDispatchService")
@TPublic
public class DispatchService {

    @Autowired
    private ServerRepo serverRepo;

    @TMethod("GetServers")
    public List getServers() throws MessageException {

        log.info("user get servers");

        return serverRepo.findAll();
    }
}
