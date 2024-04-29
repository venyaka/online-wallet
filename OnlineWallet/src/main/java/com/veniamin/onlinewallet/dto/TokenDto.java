package com.veniamin.onlinewallet.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TokenDto {
    @NotNull
    private String token;
}
