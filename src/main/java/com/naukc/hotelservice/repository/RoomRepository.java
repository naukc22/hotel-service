package com.naukc.hotelservice.repository;

import com.naukc.hotelservice.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
