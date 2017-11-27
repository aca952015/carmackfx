package com.istudio.carmackfx.domain;

import lombok.Data;

/**
 * Created by ACA on 2017-6-8.
 */
@Data
public class AuthResult {

    private User user;
    private boolean success;
}
