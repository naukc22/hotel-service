package com.example.hotelservice.service;


import com.example.hotelservice.dto.ReservationDTO;
import org.springframework.http.ResponseEntity;

public interface ReservationService {

    /**
     * Создает новую бронь
     * @param reservationDTO - бронь для создания
     */
    ResponseEntity<String> create(ReservationDTO reservationDTO);

}
