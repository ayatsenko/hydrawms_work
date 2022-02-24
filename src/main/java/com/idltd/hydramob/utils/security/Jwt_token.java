package com.idltd.hydramob.utils.security;

import java.io.Serializable;

public class Jwt_token implements Serializable {
    private String tokentype;
    private String username;
    private String organization;
    private long token_create_date;
    private long token_expiration_date;

    public Jwt_token(String tokentype, String username, String organization, long token_create_date, long token_expiration_date) {
        this.tokentype = tokentype;
        this.username = username;
        this.organization = organization;
        this.token_create_date = token_create_date;
        this.token_expiration_date = token_expiration_date;
    }
}
