package com.mindvoice.psych.web.controller;

import com.mindvoice.psych.api.dto.UserLoginRequest;
import com.mindvoice.psych.common.api.ApiResponse;
import com.mindvoice.psych.common.api.TokenResponse;
import com.mindvoice.psych.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

//    private AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody UserLoginRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtUtil.generateToken(req.getUsername());
        return  ApiResponse.success(new TokenResponse(token));
    }


//    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
//        UserDTO userDTO = authService.register(registerRequest);
//        return ResponseEntity.ok(userDTO);
//    }

}

