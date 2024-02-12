package com.kyattonippu.api.models;

import lombok.Data;

@Data
public class RegisterUserResponse {
    private int id;
    private String token;
    private String error;
}
