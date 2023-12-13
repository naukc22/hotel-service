package com.example.hotelservice.repository;

import com.example.hotelservice.models.Reservation;
import com.example.hotelservice.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("""
        SELECT NOT EXISTS (
            SELECT 1
            FROM Reservation r
            WHERE r.room = :prmRoom
              AND NOT (
                :prmDateIn >= r.dateExit OR
                :prmDateExit <= r.dateIn
              )
        ) AS is_Available
""")
    boolean isAvailable(Room prmRoom, LocalDate prmDateIn, LocalDate prmDateExit);

}
