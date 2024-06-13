package com.naukc.hotelservice.service;


import com.naukc.hotelservice.dto.DateDTO;
import com.naukc.hotelservice.dto.DayForCalendarDTO;
import com.naukc.hotelservice.dto.ReservationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReservationService {

    ResponseEntity<String> create(ReservationDTO reservationDTO);

    ResponseEntity<String> show (DateDTO dateDTO);

    List<DayForCalendarDTO> getReservationsByMonth(int month, int year);
}
