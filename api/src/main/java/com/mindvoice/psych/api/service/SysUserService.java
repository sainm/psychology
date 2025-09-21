package com.mindvoice.psych.api.service;


import com.mindvoice.psych.api.dto.SysUserDto;

public interface SysUserService {
    SysUserDto findByUsername(String username);
}
