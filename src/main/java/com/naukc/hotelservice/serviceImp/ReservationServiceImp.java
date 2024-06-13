package com.naukc.hotelservice.serviceImp;

import com.naukc.hotelservice.dto.DateDTO;
import com.naukc.hotelservice.dto.DayForCalendarDTO;
import com.naukc.hotelservice.dto.ReservationDTO;
import com.naukc.hotelservice.models.Reservation;
import com.naukc.hotelservice.models.Room;
import com.naukc.hotelservice.repository.ReservationRepository;
import com.naukc.hotelservice.service.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImp implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomServiceImp roomServiceImp;
    private final ObjectMapper objectMapper;


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
                    .build();

            reservationRepository.save(reserv);
            return ResponseEntity.status(HttpStatus.CREATED).body("Номер успешно забронирован!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Запрашиваемые даты к сожалению заняты!");
        }

    }

    @Override
    public ResponseEntity<String> show(DateDTO dateDTO) {
        LocalDate in = LocalDate.parse(dateDTO.getDateIn());
        LocalDate out = LocalDate.parse(dateDTO.getDateOut());

        List<Integer> listWithId = reservationRepository.show(in,out);   // получили список номеров которые заняты на эти даты


        List<Room> listOfRooms = roomServiceImp.findAll();  // список всех существующих номеров

        List<Room> freeRooms = listOfRooms.stream()
                .filter(room -> !listWithId.contains(room.getId()))
                .toList();

        try {
            // Преобразование списка Room в JSON
            String json = objectMapper.writeValueAsString(freeRooms);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error processing the getting rooms", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public List<DayForCalendarDTO> getReservationsByMonth(int month, int year) {

        List<DayForCalendarDTO> listOfDays = new ArrayList<>();
        String[] colors = {"Red", "Blue", "Orange", "Green"};
        List<Reservation> list = reservationRepository.getReservationsByMonth(month, year);


        for (Reservation res:
             list) {

            String roomName = res.getRoom().getRoomName();
            int resId = res.getId();
            LocalDate dateIn = res.getDateIn();
            LocalDate dateExit = res.getDateExit();
            String color = colors[resId % 4];

            for (LocalDate date = dateIn; !date.isAfter(dateExit); date = date.plusDays(1))
            {
                if (date.getMonthValue() != month) {
                    continue;
                }

                if (date.isEqual(dateIn)) {
                    listOfDays.add(new DayForCalendarDTO(resId, String.valueOf(date.getDayOfMonth()),"in", roomName, color));
                } else if (date.isEqual(dateExit)) {
                    listOfDays.add(new DayForCalendarDTO(resId, String.valueOf(date.getDayOfMonth()),"out", roomName, color));
                } else {
                    listOfDays.add(new DayForCalendarDTO(resId, String.valueOf(date.getDayOfMonth()),"full", roomName, color));
                }
            }

        }

        mergeDays(listOfDays);

        return listOfDays;
    }

    public static void mergeDays(List<DayForCalendarDTO> listOfDays) {
        List<DayForCalendarDTO> toAdd = new ArrayList<>();
        List<DayForCalendarDTO> toRemove = new ArrayList<>();

        for (int i = 0; i < listOfDays.size(); i++) {
            DayForCalendarDTO day1 = listOfDays.get(i);
            for (int j = i + 1; j < listOfDays.size(); j++) {
                DayForCalendarDTO day2 = listOfDays.get(j);

                if (day1.getDay().equals(day2.getDay()) && day1.getRoomName().equals(day2.getRoomName())) {
                    toRemove.add(day1);
                    toRemove.add(day2);
                    DayForCalendarDTO mergedDay = new DayForCalendarDTO(
                            day1.getReservationId(), day1.getDay(), "full", day1.getRoomName(), day1.getColor() + "-" + day2.getColor()
                    );
                    toAdd.add(mergedDay);
                }
            }
        }

        listOfDays.removeAll(toRemove); // Удаление найденных совпадений
        listOfDays.addAll(toAdd); // Добавление новых слиянных элементов
    }


}
