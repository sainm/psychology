package com.mindvoice.psych.infra.config;

import com.mindvoice.psych.api.service.SysUserService;
import com.mindvoice.psych.common.util.JwtUtil;
import com.mindvoice.psych.infra.domain.SysUser;
import com.mindvoice.psych.infra.mapper.SysUserMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final SysUserMapper sysUserMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "");
        String username;
        try {
            username = JwtUtil.getUsernameFromToken(token);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        SysUser user = sysUserMapper.findByUsername(username);
        if (user != null) {
            var authorities = user.getRoles().stream().flatMap(role -> role.getPermissions().stream()).map(p -> new SimpleGrantedAuthority(p.getPermissionCode())).collect(Collectors.toList());

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
