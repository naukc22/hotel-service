package com.example.hotelservice.security.service;

import com.example.hotelservice.exceptions.SecurityError;
import com.example.hotelservice.security.config.JwtService;
import com.example.hotelservice.security.dto.LoginRequestDTO;
import com.example.hotelservice.security.dto.RegisterRequestDTO;
import com.example.hotelservice.security.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(RegisterRequestDTO request) {

        if (userService.findByUsername(request.getEmail()).isPresent()) {
            return new ResponseEntity<>(new SecurityError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.createNewUser(request);
        return ResponseEntity.ok().body("Аккаунт успешно создан. Ваш id - " + user.getId());
    }

    public ResponseEntity<?> authenticate(LoginRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new SecurityError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
