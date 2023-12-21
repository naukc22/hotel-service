package com.example.hotelservice.models.security_models;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String email;
    String password;
}
