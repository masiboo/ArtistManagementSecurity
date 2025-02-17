package com.demo.security.controller;

import com.demo.security.model.AuthRequest;
import com.demo.security.model.AuthResponse;
import com.demo.security.model.UserRecord;
import com.demo.security.service.JwtService;
import com.demo.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        UserRecord user = userService.registerUser(request.email(), request.password());
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationProvider.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        var user = userService.loadUserByUsername(request.email());
        var token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
