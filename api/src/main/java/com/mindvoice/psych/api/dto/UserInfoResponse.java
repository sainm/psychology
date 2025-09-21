package com.mindvoice.psych.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponse {

    private String username;
    private String email;
    private List<String> roles;
    private List<String> permissions;
}
