package com.example.hotelservice.serviceImp;

import com.example.hotelservice.dto.ReservationDTO;
import com.example.hotelservice.models.Reservation;
import com.example.hotelservice.models.Room;
import com.example.hotelservice.repository.ReservationRepository;
import com.example.hotelservice.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImp implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomServiceImp roomServiceImp;


    @Override
    public ResponseEntity<String> create(ReservationDTO reservationDTO) {
        /** Формат даты - "yyyy-MM-dd" **/
        LocalDate in = LocalDate.parse(reservationDTO.getDateIn());
        LocalDate out = LocalDate.parse(reservationDTO.getDateExit());

        if (out.isBefore(in)){
            return ResponseEntity.status(HttpStatus.OK).body("Дата заезда позже чем дата выезда");
        }

        if (in.equals(out)) {
            return ResponseEntity.status(HttpStatus.OK).body("Минимальный срок бронирования 1 сутки");
        }

        Optional<Room> room = roomServiceImp.findById(reservationDTO.getRoom());

        if(room.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Такого номера не существует");
        }

        if(reservationRepository.isAvailable(room.get(), in, out)) {
            Reservation reserv = Reservation.builder()
                    .dateIn(in)
                    .dateExit(out)
                    .room(room.get())
                    .name(reservationDTO.getName())
                    .build();

            reservationRepository.save(reserv);
            return ResponseEntity.status(HttpStatus.CREATED).body("Номер успешно забронирован!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Запрашиваемые даты к сожалению заняты!");
        }

    }


}
