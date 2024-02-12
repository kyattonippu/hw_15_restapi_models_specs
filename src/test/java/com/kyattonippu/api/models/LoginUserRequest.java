package com.kyattonippu.api.models;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
