package com.kyattonippu.api.models;

import lombok.Data;

@Data
public class LoginUserResponse {
    private String token;
    private String error;
}
