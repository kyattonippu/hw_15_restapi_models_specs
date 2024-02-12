package com.kyattonippu.api.models;

import lombok.Data;

@Data
public class CreateAndUpdateUserRequest {
    private String name, job;
}
