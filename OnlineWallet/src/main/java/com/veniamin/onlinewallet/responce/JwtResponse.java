package com.veniamin.onlinewallet.responce;

import lombok.Getter;

@Getter
public class JwtResponse {
    private String token;
    private Long id;

    public JwtResponse(String token, Long id) {
        this.token = token;
        this.id = id;
    }
}