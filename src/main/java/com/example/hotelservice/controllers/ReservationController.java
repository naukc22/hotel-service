package com.example.hotelservice.controllers;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;


    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.create(reservationDTO);
    }



}
