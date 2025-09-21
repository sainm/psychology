package com.mindvoice.psych.core.service.impl;

import com.mindvoice.psych.api.dto.SysUserDto;
import com.mindvoice.psych.api.service.SysUserService;
import com.mindvoice.psych.infra.domain.SysUser;
import com.mindvoice.psych.infra.mapper.SysUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public SysUserDto findByUsername(String username) {
        SysUser user = sysUserMapper.findByUsername(username);
        if (user == null) {
            return null;
        }
        SysUserDto dto = new SysUserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        return dto;
    }
}
