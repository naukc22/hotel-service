package com.example.hotelservice.controllers;

import com.example.hotelservice.models.security_models.LoginRequest;
import com.example.hotelservice.models.security_models.LoginResponse;
import com.example.hotelservice.models.security_models.RegisterRequest;
import com.example.hotelservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register (@RequestBody RegisterRequest request) {
        System.out.println(request.getFirstname());
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate (@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
