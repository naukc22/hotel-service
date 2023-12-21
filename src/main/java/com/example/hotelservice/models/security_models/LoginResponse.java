package com.example.hotelservice.models.security_models;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private  String accessToken;

}
