package com.veniamin.onlinewallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class PhoneCodeDto {
    @NotBlank
    private String phone;

    @NotBlank
    private String code;
}
