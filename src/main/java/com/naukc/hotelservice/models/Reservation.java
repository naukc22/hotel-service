package com.naukc.hotelservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Формат даты - "yyyy-MM-dd" **/
    @Column(name = "date_in", nullable = false)
    private LocalDate dateIn;

    /** Формат даты - "yyyy-MM-dd" **/
    @Column(name = "date_exit", nullable = false)
    private LocalDate dateExit;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


}
