package com.example.hotelservice.security.controller;

import com.example.hotelservice.security.dto.LoginRequestDTO;
import com.example.hotelservice.security.dto.RegisterRequestDTO;
import com.example.hotelservice.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @PostMapping("/registration")
    public ResponseEntity<?> register (@RequestBody RegisterRequestDTO request) {
        return service.register(request);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate (@RequestBody LoginRequestDTO request) {
        return service.authenticate(request);
    }
}
