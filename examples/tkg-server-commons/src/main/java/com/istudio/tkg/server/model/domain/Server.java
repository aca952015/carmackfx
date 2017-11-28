package com.istudio.tkg.server.model.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Server {

    private Long id;
    private String name;
    private String host;
    private Integer port;
    private boolean isEnabled;
    private Date createTime;
    private Long createUser;
    private Date updateTime;
    private Date updateUser;
}
