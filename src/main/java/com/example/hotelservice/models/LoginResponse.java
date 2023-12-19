package com.example.hotelservice.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private final  String accessToken;

}
