package com.kyattonippu.api.models;

import lombok.Data;

@Data
public class SingleUserResponse {
    private SingleUserDataResponse data;
    private SingleUserSupportDataResponse support;
}
