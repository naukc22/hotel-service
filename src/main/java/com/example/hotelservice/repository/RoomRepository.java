package com.example.hotelservice.repository;

import com.example.hotelservice.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
