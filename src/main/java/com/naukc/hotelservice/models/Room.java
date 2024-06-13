package com.naukc.hotelservice.models;


import com.naukc.hotelservice.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Room")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    public Room(String roomName) {
        this.roomName = roomName;
    }

}
