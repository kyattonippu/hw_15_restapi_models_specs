package com.kyattonippu.api.models;

import lombok.Data;

@Data
public class CreateAndUpdateUserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
