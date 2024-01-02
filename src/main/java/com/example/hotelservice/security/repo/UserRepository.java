package com.example.hotelservice.security.repo;

import com.example.hotelservice.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    public Optional<User> findByEmail(String email);
}