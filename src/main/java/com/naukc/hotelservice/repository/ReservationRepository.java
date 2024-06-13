package com.naukc.hotelservice.repository;

import com.naukc.hotelservice.models.Reservation;
import com.naukc.hotelservice.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

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

    @Query("""
        SELECT DISTINCT r.room.id
        FROM Reservation r
        WHERE r.dateExit >= :startDate AND r.dateIn <= :endDate
    """)
    List<Integer> show(LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM Reservation r WHERE EXTRACT(MONTH FROM r.dateIn) = :month AND EXTRACT(YEAR FROM r.dateIn) = :year OR EXTRACT(MONTH FROM r.dateExit) = :month AND EXTRACT(YEAR FROM r.dateExit) = :year")
    List<Reservation> getReservationsByMonth(int month, int year);
}
