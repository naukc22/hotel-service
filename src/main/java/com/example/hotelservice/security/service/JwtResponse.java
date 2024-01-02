package com.example.hotelservice.security.service;

import lombok.Data;

@Data
public class JwtResponse {

    String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
