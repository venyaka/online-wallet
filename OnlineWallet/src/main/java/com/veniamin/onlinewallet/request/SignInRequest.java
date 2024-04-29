package com.veniamin.onlinewallet.request;

import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    @NotBlank
    private String phone;

    @NotBlank
    private String verificatinCode;

    private Set<String> role;

    public SignInRequest (String phone, String verificatinCode, Set<String> role) {
        this.phone = phone;
        this.verificatinCode = verificatinCode;
        this.role = role;
    }
}