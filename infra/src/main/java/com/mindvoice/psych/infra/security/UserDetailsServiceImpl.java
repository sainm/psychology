package com.mindvoice.psych.infra.security;

import com.mindvoice.psych.infra.domain.SysRole;
import com.mindvoice.psych.infra.domain.SysUser;
import com.mindvoice.psych.infra.mapper.SysUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserMapper userMapper;

    public UserDetailsServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(SysRole::getRoleName) .toArray(String[]::new)) // Placeholder for user roles/permissions
                .build();
    }
}
