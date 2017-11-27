package com.istudio.tkg.server.model.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Account {

    private Long id;
    private String email;
    private String phone;
    private String nickname;
    private String password;
    private boolean isEnabled;
    private Date createTime;
    private Long createUser;
    private Date updateTime;
    private Date updateUser;
}
