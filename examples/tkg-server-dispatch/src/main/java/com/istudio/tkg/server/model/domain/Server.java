package com.istudio.tkg.server.model.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Server {

    @Id
    private String id;
    private String name;
    private String host;
    private Integer port;
    private boolean isEnabled;
    private Date createTime;
    private Long createUser;
    private Date updateTime;
    private Date updateUser;
}
