package com.example.hotelservice.serviceImp;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.models.Reservation;
import com.example.hotelservice.models.Room;
import com.example.hotelservice.repository.ReservationRepository;
import com.example.hotelservice.repository.RoomRepository;
import com.example.hotelservice.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImp implements ReservationService {

    ReservationRepository reservationRepository;
    RoomRepository roomRepository;

    @Autowired
    public ReservationServiceImp(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }



    public void create(ReservationDTO reservationDTO) {
        /** Формат даты - "yyyy-MM-dd" **/
        LocalDate in = LocalDate.parse(reservationDTO.getCheck_in());
        LocalDate out = LocalDate.parse(reservationDTO.getCheck_out());

        Room room = roomRepository.findById(reservationDTO.getRoom()).orElse(null);

        /** Цикл создаёт в базе все брони для конкретного номера в заданном диапозоне дат **/
        for (LocalDate date = in; date.isBefore(out); date = date.plusDays(1)) {
            reservationRepository.save(new Reservation(date.toString(),room));
        }


    }


}
