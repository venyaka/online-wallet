package com.veniamin.onlinewallet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
    private boolean ok;
    private long userId;
}
