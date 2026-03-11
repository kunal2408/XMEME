package com.crio.starter.controller;

import com.crio.starter.entity.UserEntity;
import com.crio.starter.security.JwtTokenProvider;
import com.crio.starter.entity.Role;
import com.crio.starter.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String roleStr = request.getOrDefault("role", "USER");

        Role role = Role.valueOf(roleStr.toUpperCase());
        UserEntity user = userService.register(username, password, role);
        return ResponseEntity.ok("User registered: " + user.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        UserEntity user = userService.authenticate(username, password);
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(token);
    }
}