package com.mindvoice.psych.api.dto;

import lombok.Data;

@Data
public class SysUserDto {
    private Long id;
    private String username;
    private String email;
    private Integer status;
}
