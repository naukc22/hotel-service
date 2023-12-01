package com.example.hotelservice.service;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.models.Reservation;
import com.example.hotelservice.models.Room;
import org.springframework.stereotype.Service;


public interface ReservationService {

    /**
     * Создает новую бронь
     * @param reservationDTO - бронь для создания
     */
    void create(ReservationDTO reservationDTO);


}
