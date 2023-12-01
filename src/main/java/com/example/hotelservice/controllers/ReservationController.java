package com.example.hotelservice.controllers;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.models.Reservation;
import com.example.hotelservice.models.Room;
import com.example.hotelservice.service.ReservationService;
import com.example.hotelservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<Reservation> create(@RequestBody ReservationDTO reservationDTO) {
        reservationService.create(reservationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
