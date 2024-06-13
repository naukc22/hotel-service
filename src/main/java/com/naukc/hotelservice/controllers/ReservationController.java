package com.naukc.hotelservice.controllers;

import com.google.gson.Gson;
import com.naukc.hotelservice.dto.DateDTO;

import com.naukc.hotelservice.dto.DayForCalendarDTO;
import com.naukc.hotelservice.dto.ReservationDTO;
import com.naukc.hotelservice.service.ReservationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservation")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class ReservationController {

    private final ReservationService reservationService;


    @GetMapping(value = "/getReservationsByMonth", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getReservationsByMonth(@RequestParam int month, @RequestParam int year) {
        List<DayForCalendarDTO> list = reservationService.getReservationsByMonth(month, year);
        String json = new Gson().toJson(list);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> create(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.create(reservationDTO);
    }


    @PostMapping(value = "/show", produces = "application/json")
    public ResponseEntity<String> show(@RequestBody DateDTO dateDTO) {

        System.out.println("ДАТЫ ПРИШЛИ ТАКИЕ : ");
        System.out.println(dateDTO.getDateIn());
        System.out.println(dateDTO.getDateOut());
        return reservationService.show(dateDTO);
    }


}
