package com.kyattonippu.api.models;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String email;
    private String password;
}
